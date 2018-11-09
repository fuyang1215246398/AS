package es.source.code.br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import es.source.code.activity.SCOSEntry;
import es.source.code.service.UpdateService;

public class DeviceStartedListener extends BroadcastReceiver {
    private static final String ACTION_SHUTDOWN = "android.intent.action.ACTION_SHUTDOWN";
    private static final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (intent.getAction().toString().equals(ACTION_BOOT)) { //开机启动完成后，要做的事情
            Intent Nintent=new Intent(context,UpdateService.class);
            context.startService(Nintent);
        }
    }
}
