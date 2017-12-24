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

import java.io.File;

import kasper.android.store_manager.R;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.models.memory.Category;
import kasper.android.store_manager.models.memory.Customer;
import kasper.android.store_manager.models.memory.Event;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class OrderCreateActivity extends AppCompatActivity {

    ImageView photoIV;
    EditText nameET;

    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_create);

        this.initViews();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {

                Toast.makeText(OrderCreateActivity.this, "عکس اتخاب نشد", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {

                Toast.makeText(OrderCreateActivity.this, "عکس با موفقیت اتخاب شد", Toast.LENGTH_SHORT).show();

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 3;

                Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getPath());

                OrderCreateActivity.this.photoIV.setImageBitmap(bitmap);
            }
        });

        if (requestCode == 123) {

            if (resultCode == RESULT_OK) {

                customer = (Customer) data.getExtras().getSerializable("customer");
            }
        }
    }

    public void onPhotoBtnClicked(View view) {
        EasyImage.openChooserWithDocuments(this, "", 0);
    }

    public void onPickCustomerBtnClicked(View view) {
        startActivityForResult(new Intent(this, CustomerPickerActivity.class), 123);
    }

    public void onCancelBtnClicked(View view) {
        this.finish();
    }

    public void onOkBtnClicked(View view) {

        String name = this.nameET.getText().toString();

        if (name.length() == 0) {
            Toast.makeText(this, "نام باید وارد شد", Toast.LENGTH_SHORT).show();
            return;
        }

        if (customer == null) {
            Toast.makeText(this, "مشتری باید انتخاب شود", Toast.LENGTH_SHORT).show();
            return;
        }

        int id = Core.getInstance().getDatabaseHelper().addOrder(name, customer);

        Core.getInstance().getDatabaseHelper().addEvent("سفارش جدید به سیستم اضافه شد .", Event.EventAttachmentTypes.ORDER, id);

        this.finish();
    }

    private void initViews() {
        this.photoIV = this.findViewById(R.id.activity_order_create_photo_image_view);
        this.nameET = this.findViewById(R.id.activity_order_create_name_edit_text);
    }
}
