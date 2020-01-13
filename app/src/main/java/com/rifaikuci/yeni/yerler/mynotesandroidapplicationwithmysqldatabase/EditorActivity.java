package com.rifaikuci.yeni.yerler.mynotesandroidapplicationwithmysqldatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.rifaikuci.yeni.yerler.mynotesandroidapplicationwithmysqldatabase.Note;
import com.thebluealliance.spectrum.SpectrumPalette;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorActivity extends AppCompatActivity {

    EditText et_title, et_note;
    ProgressDialog progressDialog;
    SpectrumPalette palette;
    ApiInterface apiInterface;
    int color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        et_title = (EditText) findViewById(R.id.title);
        et_note = (EditText) findViewById(R.id.note);
        palette = (SpectrumPalette) findViewById(R.id.palette);

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
                    saveNote(title,note,color);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote(final String title, final  String note, final  int color) {
        progressDialog.show();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Note> call = apiInterface.saveNote(title,note,color);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call,@NonNull Response<Note> response) {
                progressDialog.dismiss();

                if(response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().getSuccess();

                    if(success){
                        Toast.makeText(EditorActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        finish();
                    }else
                    {
                        Toast.makeText(EditorActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        System.out.println("yazdır"+response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Note> call,@NonNull Throwable t) {
                Toast.makeText(EditorActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                finish();
            }
        });





    }
}
