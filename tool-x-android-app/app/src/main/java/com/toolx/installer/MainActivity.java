package com.toolx.installer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCheckTermux = findViewById(R.id.btn_check_termux);
        Button btnInstallToolX = findViewById(R.id.btn_install_tool_x);
        Button btnLaunchToolX = findViewById(R.id.btn_launch_tool_x);

        btnCheckTermux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = getPackageManager().getLaunchIntentForPackage("com.termux");
                    if (intent != null) {
                        Toast.makeText(MainActivity.this, "Termux is installed!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    // Termux is not installed, open F-Droid
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://f-droid.org/en/packages/com.termux/"));
                    startActivity(intent);
                }
            }
        });

        btnInstallToolX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent to open Termux with the installation command
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("termux://"));
                intent.putExtra("com.termux.execute", 
                    "pkg install curl -y && " +
                    "curl -O https://raw.githubusercontent.com/OniXinO/Tool-X-ekadanuarta/main/tool-x-android/install-termux.sh && " +
                    "chmod +x install-termux.sh && " +
                    "./install-termux.sh");
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Termux not found. Please install it first.", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnLaunchToolX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent to open Termux and launch Tool-X
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("termux://"));
                intent.putExtra("com.termux.execute", "Tool-X");
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Termux not found. Please install it first.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
