package tw.android.test.activity.socket;

import android.util.Log;

import microsoft.aspnet.signalr.client.Logger;
import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;
import microsoft.aspnet.signalr.client.transport.ClientTransport;
import microsoft.aspnet.signalr.client.transport.ServerSentEventsTransport;

public class SignalR {
    public static String SIGNAL_R_HUB = "TickerHub";
    public static String SEND_TICKERS = "sendTickersByNationID";
    public static String SEND_PRE_ORDER = "sendPreOrderByNationID";
    public static String SUBSCRIBE_TICKERS = "SubscribeTickersByNationID";
    public static String WEB_SOCKET_URL = "http://api-trading.git4u.net:5263/";
    // Fields from product flavor: bibi2u
    public static final String Nation_ID = "4";
    private static final SignalR instance = new SignalR();
    private final String TAG = SignalR.class.getSimpleName();
    private HubConnection conn;
    private ClientTransport transport;
    private HubProxy proxy;
    private OnInvokeMessageListener listener;

    private SignalR() {
        Platform.loadPlatformComponent(new AndroidPlatformComponent());
    }

    public static SignalR getInstance() {
        return instance;
    }

    public SignalR initConnection() {
        try {
            if (conn == null) {
                conn = new HubConnection(WEB_SOCKET_URL);

                Logger logger = (message, logLevel) -> {
                    Log.d("Logger", "LogLevel " + logLevel + " Message " + message + "\n");
                };

                transport = new ServerSentEventsTransport(logger);
                proxy = conn.createHubProxy(SIGNAL_R_HUB);

                // Subscribe to the error event
                conn.error(throwable -> {
                    throwable.printStackTrace();
                    sendError(throwable.getMessage());
                });

                // Subscribe to the connected event
                conn.connected(() -> Log.w(TAG, "SignalR onConnected"));

                // Subscribe to the closed event
                conn.closed(() -> Log.w(TAG, "SignalR onClosed"));

                // Subscribe to the received event
//        conn.received(new MessageReceivedHandler() {
//            @Override
//            public void onMessageReceived(JsonElement json) {
//                Log.e("SignalR", "onMessageReceived " + json.toString());
//            }
//        });

                proxy.on(SEND_TICKERS, message -> {
                    if (listener != null) {
                        listener.onMessageReceived(SEND_TICKERS, message);
                    }
                }, String.class);

                proxy.on(SEND_PRE_ORDER, message -> {
                    if (listener != null) {
                        listener.onMessageReceived(SEND_PRE_ORDER, message);
                    }
                }, String.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendError(e.getMessage());
        }

        return this;
    }

    public SignalR setInvokeMessageListener(OnInvokeMessageListener listener) {
        this.listener = listener;
        return this;
    }

    public SignalR start() {

        try {
            if (conn != null) {
                conn.start(transport).done(aVoid -> {
                    if (listener != null) {
                        listener.onConnected();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendError(e.getMessage());
        }

        return this;
    }

    public void stop() {
        try {
            if (conn != null) {
                conn.disconnect();
                conn.stop();
                conn = null;
                proxy = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendError(e.getMessage());
        }
    }

    public SignalRFuture<String> invoke(String method, String... args) {
        try {
            if (proxy != null) {
                return proxy.invoke(String.class, method, (Object[]) args);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendError(e.getMessage());
        }

        return new SignalRFuture<>();
    }

    private void sendError(String message) {
        if (listener != null) {
            listener.onError(message);
        }
    }

    public interface OnInvokeMessageListener {
        void onConnected();

        void onMessageReceived(String sendMethod, String message);

        void onError(String message);
    }
}
