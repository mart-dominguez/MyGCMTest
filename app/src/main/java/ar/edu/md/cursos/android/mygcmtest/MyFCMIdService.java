package ar.edu.md.cursos.android.mygcmtest;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class MyFCMIdService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Refreshed token: SERVICIO CREADO!!!!");

    }

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        saveTokenToPrefs(refreshedToken);
    }

    private void saveTokenToPrefs(String _token)
    {
        // Access Shared Preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        // Save to SharedPreferences
        editor.putString("registration_id", _token);
        editor.apply();
    }

    private String getTokenFromPrefs()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString("registration_id", null);
    }
}
