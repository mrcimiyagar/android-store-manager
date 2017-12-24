package kasper.android.store_manager.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.io.File;

import kasper.android.store_manager.R;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.models.memory.Category;
import kasper.android.store_manager.models.memory.Event;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class CustomerCreateActivity extends AppCompatActivity {

    ImageView photoIV;
    EditText nameET;
    EditText emailET;
    EditText phoneET;
    EditText addressET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_create);

        this.initViews();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {

                Toast.makeText(CustomerCreateActivity.this, "عکس اتخاب نشد", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {

                Toast.makeText(CustomerCreateActivity.this, "عکس با موفقیت اتخاب شد", Toast.LENGTH_SHORT).show();

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 3;

                Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getPath());

                CustomerCreateActivity.this.photoIV.setImageBitmap(bitmap);
            }
        });
    }

    public void onPhotoBtnClicked(View view) {
        EasyImage.openChooserWithDocuments(this, "", 0);
    }

    public void onCancelBtnClicked(View view) {
        this.finish();
    }

    public void onOkBtnClicked(View view) {

        String name = this.nameET.getText().toString();
        String email = this.emailET.getText().toString();
        String phone = this.phoneET.getText().toString();
        String address = this.addressET.getText().toString();

        if (name.length() == 0) {
            Toast.makeText(this, "نام باید وارد شد", Toast.LENGTH_SHORT).show();
            return;
        }

        int id = Core.getInstance().getDatabaseHelper().addCustomer(name, phone, email, address);

        Core.getInstance().getDatabaseHelper().addEvent("مشتری جدید به سیستم اضافه شد .", Event.EventAttachmentTypes.CUSTOMER, id);

        this.finish();
    }

    private void initViews() {
        this.photoIV = this.findViewById(R.id.activity_customer_create_photo_image_view);
        this.nameET = this.findViewById(R.id.activity_customer_create_name_edit_text);
        this.emailET = this.findViewById(R.id.activity_customer_create_email_edit_text);
        this.phoneET = this.findViewById(R.id.activity_customer_create_phone_number_edit_text);
        this.addressET = this.findViewById(R.id.activity_customer_create_address_edit_text);
    }
}
