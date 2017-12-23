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
import kasper.android.store_manager.models.memory.ItemType;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class ItemCreateActivity extends AppCompatActivity {

    ImageView photoIV;
    EditText countET;

    long deadlineTime;

    ItemType itemType;

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

                itemType = (ItemType) data.getExtras().get("item-type");
            }
        }
    }

    public void onPickItemTypeBtnClicked(View view) {
        startActivityForResult(new Intent(this, ItemTypePickerActivity.class), 123);
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

        int count = Integer.parseInt(this.countET.getText().toString());

        Core.getInstance().getDatabaseHelper().addItem(itemType, count, "", deadlineTime);

        this.finish();
    }

    private void initViews() {
        this.countET = this.findViewById(R.id.activity_item_create_count_edit_text);
    }
}
