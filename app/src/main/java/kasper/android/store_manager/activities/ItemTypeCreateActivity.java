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

public class ItemTypeCreateActivity extends AppCompatActivity {

    ImageView photoIV;
    EditText nameET;
    EditText priceET;

    Category parentCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_type_create);

        this.initViews();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {

                Toast.makeText(ItemTypeCreateActivity.this, "عکس اتخاب نشد", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {

                Toast.makeText(ItemTypeCreateActivity.this, "عکس با موفقیت اتخاب شد", Toast.LENGTH_SHORT).show();

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 3;

                Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getPath());

                ItemTypeCreateActivity.this.photoIV.setImageBitmap(bitmap);
            }
        });

        if (requestCode == 123) {

            if (resultCode == RESULT_OK) {

                parentCategory = (Category) data.getExtras().getSerializable("category");
            }
        }
    }

    public void onPhotoBtnClicked(View view) {
        EasyImage.openChooserWithDocuments(this, "", 0);
    }

    public void onPickCategoryBtnClicked(View view) {
        startActivityForResult(new Intent(this, CategoryPickerActivity.class), 123);
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

        int price = 0;

        try {
            price = Integer.parseInt(this.priceET.getText().toString());
        }
        catch (NumberFormatException ignored) {

        }

        if (price == 0) {
            Toast.makeText(this, "قیمت نامعتبر", Toast.LENGTH_SHORT).show();
            return;
        }

        int id = Core.getInstance().getDatabaseHelper().addItemType(name, price, parentCategory);

        Core.getInstance().getDatabaseHelper().addEvent("نوع کالای جدید به سیستم اضافه شد .", Event.EventAttachmentTypes.ITEM_TYPE, id);

        this.finish();
    }

    private void initViews() {
        this.photoIV = this.findViewById(R.id.activity_item_type_create_photo_image_view);
        this.nameET = this.findViewById(R.id.activity_item_type_create_name_edit_text);
        this.priceET = this.findViewById(R.id.activity_item_type_create_price_edit_text);
    }
}
