package com.example.lantar.solveasttest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by Lantar on 01.12.2015.
 */
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Options extends ActionBarActivity
{

    private static int interval;
    private EditText editInterval;
    private String path;
    private SharedPreferences sharedPreferences;
    final String PATH = "PATH";
    private int mSeconds = 1000;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        editInterval = (EditText) findViewById(R.id.editInterval);
        Button dirChooserButton = (Button) findViewById(R.id.chooseDirButton);
        dirChooserButton.setOnClickListener(new OnClickListener() {
            private String m_chosenDir = "";
            private boolean m_newFolderEnabled = true;

            @Override
            public void onClick(View v) {
                // Create DirectoryChooserDialog and register a callback
                DirectoryChooserDialog directoryChooserDialog =
                        new DirectoryChooserDialog(Options.this,
                                new DirectoryChooserDialog.ChosenDirectoryListener() {
                                    @Override
                                    public void onChosenDir(String chosenDir) {
                                        m_chosenDir = chosenDir;
                                        Toast.makeText(
                                                Options.this, "Chosen directory: " +
                                                        chosenDir, Toast.LENGTH_LONG).show();
                                        path = chosenDir;
                                    }
                                });
                // Toggle new folder button enabling
                directoryChooserDialog.setNewFolderEnabled(m_newFolderEnabled);
                // Load directory chooser dialog for initial 'm_chosenDir' directory.
                // The registered callback will be called upon final directory selection.
                directoryChooserDialog.chooseDirectory(m_chosenDir);
                m_newFolderEnabled = !m_newFolderEnabled;
            }
        });

        findViewById(R.id.save).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(editInterval)) {
                    if (Integer.parseInt(editInterval.getText().toString()) < 1
                            || Integer.parseInt(editInterval.getText().toString()) > 60) {
                        Toast.makeText(getApplicationContext(), Options.this.getString(R.string.fail_interval), Toast.LENGTH_SHORT).show();
                    } else {
                        interval = Integer.parseInt(editInterval.getText().toString()) * mSeconds;

                        save();
                    }
                } else {
                    interval = 4 * mSeconds;
                    save();
                }
            }
        });

    }

    private void save(){

        Intent slideShowIntent = new Intent(getApplicationContext(), SlideShowActivity.class);
        startActivity(slideShowIntent);
        OptionsDate.getInstance().setPath(path);
        OptionsDate.getInstance().setInterval(interval);
            OptionsDate.setShange(1);
        finish();
    }

    private boolean isEmpty(EditText etText) {
        String str = etText.getText().toString().trim();
        if(str.equals("") || str==null) {
            return true;
        } else {
            return false;
        }
    }

}