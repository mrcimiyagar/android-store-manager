package kasper.android.store_manager.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class ItemCreateActivity extends AppCompatActivity {

    ImageView photoIV;
    EditText nameET;
    EditText priceET;
    EditText countET;

    Category parentCategory;

    long deadlineTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_create);

        this.initViews();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {

                Toast.makeText(ItemCreateActivity.this, "عکس اتخاب نشد", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {

                Toast.makeText(ItemCreateActivity.this, "عکس با موفقیت اتخاب شد", Toast.LENGTH_SHORT).show();

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 3;

                Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getPath());

                ItemCreateActivity.this.photoIV.setImageBitmap(bitmap);
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

    public void onPickDeadlineTimeBtnClicked(View view) {
        final PersianCalendar persianCalendar = new PersianCalendar();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

                        PersianCalendar tempC = new PersianCalendar();
                        tempC.setPersianDate(year, monthOfYear, dayOfMonth);

                        deadlineTime = tempC.getTimeInMillis();

                    }
                }, persianCalendar.getPersianYear(),
                persianCalendar.getPersianMonth(),
                persianCalendar.getPersianDay()
        );
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }

    public void onAttachBarcodeBtnClicked(View view) {
        startActivity(new Intent(this, BarcodeScannerActivity.class));
    }

    public void onCancelBtnClicked(View view) {
        this.finish();
    }

    public void onOkBtnClicked(View view) {

        String name = this.nameET.getText().toString();
        int price = Integer.parseInt(this.priceET.getText().toString());
        int count = Integer.parseInt(this.countET.getText().toString());

        Core.getInstance().getDatabaseHelper().addItem(name, price, count, parentCategory, "", deadlineTime);

        this.finish();
    }

    private void initViews() {
        this.photoIV = this.findViewById(R.id.activity_item_create_photo_image_view);
        this.nameET = this.findViewById(R.id.activity_item_create_name_edit_text);
        this.priceET = this.findViewById(R.id.activity_item_create_price_edit_text);
        this.countET = this.findViewById(R.id.activity_item_create_count_edit_text);
    }
}
