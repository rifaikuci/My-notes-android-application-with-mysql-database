package com.rifaikuci.yeni.yerler.mynotesandroidapplicationwithmysqldatabase.activity.editor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.rifaikuci.yeni.yerler.mynotesandroidapplicationwithmysqldatabase.R;
import com.rifaikuci.yeni.yerler.mynotesandroidapplicationwithmysqldatabase.api.ApiInterface;
import com.thebluealliance.spectrum.SpectrumPalette;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorActivity extends AppCompatActivity implements  EditorView {

    EditText et_title, et_note;
    ProgressDialog progressDialog;
    SpectrumPalette palette;
    ApiInterface apiInterface;
    int color;
    EditorPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        et_title = (EditText) findViewById(R.id.title);
        et_note = (EditText) findViewById(R.id.note);
        palette = (SpectrumPalette) findViewById(R.id.palette);

        presenter = new EditorPresenter(this);
        palette.setOnColorSelectedListener(

                clr->color =clr);
        color = getResources().getColor(R.color.white);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait....");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.save:

                String title = et_title.getText().toString().trim();
                String note  = et_note.getText().toString().trim();
                int color = this.color;

                if(title.isEmpty()){
                        et_title.setError("Please Enter a Title");
                }
                else if(title.isEmpty()){
                    et_note.setError("Please Enter a Note");
                }else {
                    presenter.saveNote(title,note,color);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();

    }

    @Override
    public void onAddSuccess(String message) {
        Toast.makeText(EditorActivity.this,message,Toast.LENGTH_SHORT).show();
                      finish();

    }

    @Override
    public void onAddError(String message) {
        Toast.makeText(EditorActivity.this,message,Toast.LENGTH_SHORT).show();
    }
}
