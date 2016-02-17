package intelectix.wallpaper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImagePreviewActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        //Obtenemos los datos del intent
        Intent intent = getIntent();
        Integer resourceId = intent.getExtras().getInt("id");
        imageView = (ImageView)findViewById(R.id.fullImageView);
        imageView.setImageResource(resourceId);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
