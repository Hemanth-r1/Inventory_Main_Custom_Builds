package in.hr.android.inventorymain;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Convert_Quotation_To_PDFSQLActivity extends AppCompatActivity {

    String warrantyHeading = "Warranty and Support: ";
    String warranty1 = " 1. All PC built by us will receive ON-SITE warranty for TWO YEARS and FREE technical support.";
    String warranty2 = "  After two years, depending on the COMPONENTS used, warranty will be provided. ";
    String warranty3 = "2. Warranty and Replacement of Individual part depends on the MANUFACTURER.";
    String warranty4 = "3. Technical support and future upgrade are available.";

    String policyHeading = "Company Policies : ";
    String policy1 = "1. Returns are accepted within a week of purchase.";
    String policy2 = "2. Returns are not accepted without a valid reasons.";
    String policy3 = "3. Physical damage of any ELECTRONIC PARTS are not accepted.";
    String policy4 = "4. Burn out due to power from the wall, without proper EARTHING / GROUNDING,";
    String policy5 = "   are subjected to ENQUIRY, if valid, returns are available ";
    String policy6 = "5. Returns can be claimed IF AND ONLY IF the problem is not resolved.";

    int totalAmount, quoteNo;
    int serialReturn;
    String billName;
    String numberText;

    EditText customerBillName, customerMobileNumber;

    EditText processor_name, motherboard_name, ram_name, graphics_card_name, power_supply_name,
            ssd_name, hdd_name, cooler_name, cabinet_name, case_fans_name, keyboard_name,
            mouse_name, monitor_name, service_name, cable_name, headset_name;

    EditText processor_price, motherboard_price, ram_price, graphics_card_price, power_supply_price,
            ssd_price, hdd_price, cooler_price, cabinet_price, case_fans_price, keyboard_price,
            mouse_price, monitor_price, service_price, cable_price, headset_price;

    CheckBox processor, motherboard, ram, graphics_card, power_supply, ssd, hdd, cooler, cabinet,
            case_fans, keyboard, mouse, monitor, service, cable, headset;

    EditText processor_description, motherboard_description, ram_description, graphics_card_description,
            power_supply_description, ssd_description, hdd_description, cooler_description, cabinet_description,
            case_fans_description, keyboard_description,
            mouse_description, monitor_description, service_description, cable_description, headset_description;

    Button saveAndPrint, printOld;

    String processorText, motherboardText, ramText, graphicsCardText,
            ssdText, hddText, powerSupplyText, headsetText, keyboardText,
            mouseText, cabinetText, monitorText, caseFansText, cableText, serviceText, coolerText;

    String processorDescription, motherboardDescription, ramDescription, graphics_cardDescription,
            power_supplyDescription, ssdDescription, hddDescription, coolerDescription, cabinetDescription,
            case_fansDescription, keyboardDescription,
            mouseDescription, monitorDescription, serviceDescription, cableDescription, headsetDescription;

    int processorPrice = 0;
    int motherboardPrice = 0;
    int ramPrice = 0;
    int graphicsCardPrice = 0;
    int ssdPrice = 0;
    int hddPrice = 0;
    int powerSupplyPrice = 0;
    int headsetPrice = 0;
    int keyboardPrice = 0;
    int mousePrice = 0;
    int cabinetPrice = 0;
    int monitorPrice = 0;
    int caseFansPrice = 0;
    int cablePrice = 0;
    int servicePrice = 0;
    int coolerPrice = 0;

    Convert_Quotation_To_PDF_Helper_SQL convertQuotationToPdfHelperSql;
    SQLiteDatabase sqLiteDatabase;
    Date date = new Date();

    String datePattern = "dd-MM-YYYY";
    SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

    String timePattern = "hh:mm a";
    SimpleDateFormat timeFormat = new SimpleDateFormat(timePattern);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_quotation_to_pdfsql);
        callFindViewByID();

       // convertQuotationToPdfHelperSql = new Convert_Quotation_To_PDF_Helper_SQL(this);
       // sqLiteDatabase = convertQuotationToPdfHelperSql.getWritableDatabase();
    }

    private void callFindViewByID() {

        customerBillName = findViewById(R.id.editTextBillName);
        customerMobileNumber = findViewById(R.id.editTextCustomerNumber);

        printOld = findViewById(R.id.oldPrintBtn);
        saveAndPrint = findViewById(R.id.btnSaveAndPrint);

        processor = findViewById(R.id.checkBoxProcessor);
        processor_name = findViewById(R.id.editTextProcessorName);
        processor_price = findViewById(R.id.editTextProcessorPrice);
        processor_description = findViewById(R.id.processorDescription);

        motherboard = findViewById(R.id.checkBoxMotherBoard);
        motherboard_name = findViewById(R.id.editTextMotherboardName);
        motherboard_price = findViewById(R.id.editTextMotherboardPrice);
        motherboard_description = findViewById(R.id.motherboardDescription);

        ram = findViewById(R.id.checkBoxRam);
        ram_name = findViewById(R.id.editTextRamName);
        ram_price = findViewById(R.id.editTextRamPrice);
        ram_description = findViewById(R.id.ramDescription);

        graphics_card = findViewById(R.id.checkBoxGraphicsCard);
        graphics_card_name = findViewById(R.id.editTextGraphicsCardName);
        graphics_card_price = findViewById(R.id.editTextGraphicsCardPrice);
        graphics_card_description = findViewById(R.id.graphicsCardDescription);

        ssd = findViewById(R.id.checkBoxSSD);
        ssd_name = findViewById(R.id.editTextSSDName);
        ssd_price = findViewById(R.id.editTextSSDPrice);
        ssd_description = findViewById(R.id.SSDDescription);

        hdd = findViewById(R.id.checkBoxHDD);
        hdd_name = findViewById(R.id.editTextHDDName);
        hdd_price = findViewById(R.id.editTextHDDPrice);
        hdd_description = findViewById(R.id.HDDDescription);

        power_supply = findViewById(R.id.checkBoxPowerSupply);
        power_supply_name = findViewById(R.id.editTextPowerSupplyName);
        power_supply_price = findViewById(R.id.editTextPowerSupplyPrice);
        power_supply_description = findViewById(R.id.powerSupplyDescription);

        cooler = findViewById(R.id.checkBoxCooler);
        cooler_name = findViewById(R.id.editTextCoolerName);
        cooler_price = findViewById(R.id.editTextCoolerPrice);
        cooler_description = findViewById(R.id.coolerDescription);

        cabinet = findViewById(R.id.checkBoxCabinet);
        cabinet_name = findViewById(R.id.editTextCabinetName);
        cabinet_price = findViewById(R.id.editTextCabinetPrice);
        cabinet_description = findViewById(R.id.cabinetDescription);

        case_fans = findViewById(R.id.checkBoxCaseFans);
        case_fans_name = findViewById(R.id.editTextCaseFansName);
        case_fans_price = findViewById(R.id.editTextCaseFansPrice);
        case_fans_description = findViewById(R.id.caseFansDescription);

        keyboard = findViewById(R.id.checkBoxKeyboard);
        keyboard_name = findViewById(R.id.editTextKeyboardName);
        keyboard_price = findViewById(R.id.editTextKeyboardPrice);
        keyboard_description = findViewById(R.id.keyboardDescription);

        mouse = findViewById(R.id.checkBoxMouse);
        mouse_name = findViewById(R.id.editTextMouseName);
        mouse_price = findViewById(R.id.editTextMousePrice);
        mouse_description = findViewById(R.id.mouseDescription);

        monitor = findViewById(R.id.checkBoxMonitor);
        monitor_name = findViewById(R.id.editTextMonitorName);
        monitor_price = findViewById(R.id.editTextMonitorPrice);
        monitor_description = findViewById(R.id.monitorDescription);

        cable = findViewById(R.id.checkBoxExtras);
        cable_name = findViewById(R.id.editTextExtrasName);
        cable_price = findViewById(R.id.editTextExtrasPrice);
        cable_description = findViewById(R.id.extrasDescription);

        headset = findViewById(R.id.checkBoxHeadSet);
        headset_name = findViewById(R.id.editTextHeadsetName);
        headset_price = findViewById(R.id.editTextHeadsetPrice);
        headset_description = findViewById(R.id.headsetDescription);

        service = findViewById(R.id.checkBoxService);
        service_name = findViewById(R.id.editTextServiceName);
        service_price = findViewById(R.id.editTextServicePrice);
        service_description = findViewById(R.id.serviceDescription);
    }

    public void saveAndPrint(View view) {

        int serialNo = 1;

        // global page width and height
        int totalPageWidth = 595;
        int totalPageHeight = 841;

        int currentPageWidth = 0;
        int currentPageHeight = 0;

        int currentPositionWidth = 45;// for serial number width is +45
        int currentPositionHeight = 185;

        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();

        billName = customerBillName.getText().toString();
        numberText = customerMobileNumber.getText().toString();

        // data to be retrieved
        //String[] column = {"quoteNo","date", "numberText" , "processorText","processorPrice", "processorDescription",  "motherboardText", "motherboardPrice", "motherboardDescription","ramText", "ramPrice", "ramDescription", "graphicsCardText", "graphicsCardPrice","graphics_cardDescription" , "ssdText", "ssdPrice", "ssdDescription", "amount" };

       // Cursor cursor = sqLiteDatabase.query("QuoteTABLEMain", column, null, null, null, null, null);
        //cursor.move(cursor.getCount());

            /*
             for A4 size sheet width = 20.98 and height = 29.66, builder below used POST SCRIPT as unit
             which in turn is defined as 1/72 inch
            so divide the STANDARD WIDTH and HEIGHT in CM by 2.54 [result inch]
            multiply the value by 72 to get POST SCRIPT value
            Width = 20.98 cm for A4
            Height  = 29.66 cm for A4
            POSTSCRIPT width =  20.98 / 2.54 = 8.26 => 8.26 * 72 = 594.70 => 595 int
            POSTSCRIPT height = 29.66 / 2.54 = 11.67 => 11.67 * 72 = 840.75 => 841 int => 842
             */

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(totalPageWidth, totalPageHeight, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        // draw outline left
        canvas.drawRect(currentPageWidth + 10, currentPageHeight + 10, currentPageWidth + 12, totalPageHeight - 10, paint);
        // right
        canvas.drawRect(totalPageWidth - 10, currentPageHeight + 10, totalPageWidth - 12, totalPageHeight - 10, paint);
        //top
        canvas.drawRect(currentPageWidth + 10, currentPageHeight + 10, totalPageWidth - 10, currentPageHeight + 12, paint);
        //bottom
        canvas.drawRect(currentPageWidth + 10, totalPageHeight - 10, totalPageWidth - 10, totalPageHeight - 12, paint);

        //first text
        paint.setTextSize(15);
        canvas.drawText("Custom Builds", currentPageWidth + 250, currentPageHeight + 30, paint);

        //second text
        paint.setTextSize(8);
        canvas.drawText("#21, 1st Main, Rajeev Gandhi Nagar, Nandini Layout, Bengaluru 96", currentPageWidth + 200, currentPageHeight + 45, paint);

        canvas.drawText("Quotation Number", totalPageWidth - 120, currentPageHeight + 40, paint);
        canvas.drawText(String.valueOf(quoteNo), totalPageWidth - 40, currentPageHeight + 40, paint);

        canvas.drawRect(currentPageWidth + 10, currentPageHeight + 50, totalPageWidth - 10, currentPageHeight + 52, paint);

        canvas.drawText("Date :", currentPageWidth + 40, currentPageHeight + 60, paint);
        canvas.drawText(dateFormat.format(date.getTime()), 70, 60, paint);

        canvas.drawText("Time :", currentPageWidth + 150, currentPageHeight + 60, paint);
        //canvas.drawText(timeFormat.format(cursor.getLong(3)), canvas.getWidth() - 40, 200, paint);
        canvas.drawText(timeFormat.format(date.getTime()), 180, 60, paint);

        paint.setTextSize(10);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("JUST A QUOTE", currentPageWidth + 250, currentPageHeight + 70, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // billing from
        paint.setTextSize(9);
        paint.setColor(Color.rgb(150, 150, 150));
        canvas.drawRect(currentPageWidth + 15, currentPageHeight + 65, currentPageWidth + 45, currentPageHeight + 75, paint);

        paint.setColor(Color.WHITE);
        canvas.drawText("FROM", currentPageWidth + 16, currentPageHeight + 74, paint);

        paint.setColor(Color.BLACK);
        canvas.drawText("KIRAN R ", currentPageWidth + 20, currentPageHeight + 85, paint);
        canvas.drawText("CUSTOM BUILDS TECHNOLOGY", currentPageWidth + 20, currentPageHeight + 100, paint);
        canvas.drawText("NANDINI LAYOUT, BENGALURU 96", currentPageWidth + 20, currentPageHeight + 115, paint);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("MOBILE NUMBER: 9739942971", currentPageWidth + 20, currentPageHeight + 130, paint);

        canvas.drawText("GST IN: 29JVVPK7688R1ZL", totalPageWidth - 150, currentPageHeight + 100, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // draw divider line
        canvas.drawRect(currentPageWidth + 10, currentPageHeight + 135, totalPageWidth - 10, currentPageHeight + 137, paint);

        // create rectangular bar
        paint.setColor(Color.rgb(150, 150, 150));
        canvas.drawRect(currentPageWidth + 25, currentPageHeight + 150, totalPageWidth - 25, currentPageHeight + 170, paint);

        //table heading
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setColor(Color.WHITE);
        canvas.drawText("Sl no", currentPageWidth + 35, currentPageHeight + 165, paint);
        canvas.drawText("Part", currentPageWidth + 95, currentPageHeight + 165, paint);
        canvas.drawText("Brand", currentPageWidth + 175, currentPageHeight + 165, paint);
        canvas.drawText("Description", currentPageWidth + 355, currentPageHeight + 165, paint);
        canvas.drawText("Amount", currentPageWidth + 525, currentPageHeight + 165, paint);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setColor(Color.BLACK);

        int differentialWidthTextView = 40;
        int differentialWidthText = 130;
        int differentialWidthDescription = 220;
        int differentialWidthPrice = 470;

        if (processor.isChecked()) {
            if (processor_name.getText().toString().equals(null)) {
                processor_name.setText("");
            }
            processorText = processor_name.getText().toString();
            processorPrice = Integer.parseInt(processor_price.getText().toString());

            if (processor_description.getText().toString().equals(null)) {
                processor_description.setText("");
            }
            processorDescription = processor_description.getText().toString();

            canvas.drawText(String.valueOf(serialNo), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("Processor", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(processorText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(processorDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS.  " + String.valueOf(processorPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            // canvas.drawRect(currentPageWidth + 25, currentPageHeight + 155, totalPageWidth - 25, currentPageHeight +156, paint);
            updateSerialNo(serialNo);
        }
        if (motherboard.isChecked()) {
            //TODO: check
            currentPositionHeight = currentPositionHeight + 20;
            if (motherboard_name.getText().toString().equals(null)) {
                motherboard_name.setText("");
            } else {
                motherboardText = motherboard_name.getText().toString();
            }
            motherboardPrice = Integer.parseInt(motherboard_price.getText().toString());
            if (motherboard_description.getText().toString().equals(null)) {
                motherboard_description.setText("");
            } else {
                motherboardDescription = motherboard_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//165
            canvas.drawText("Motherboard", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(motherboardText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(motherboardDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS. " + String.valueOf(motherboardPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }

        if (ram.isChecked()) {
            currentPositionHeight = currentPositionHeight + 20;
            ramText = ram_name.getText().toString();
            ramPrice = Integer.parseInt(ram_price.getText().toString());
            if (ram_description.getText().toString().equals(null)) {
                ram_description.setText("");
            } else {
                ramDescription = ram_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint); //185
            canvas.drawText("Ram", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(ramText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(ramDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS. " + String.valueOf(ramPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint); +185
            updateSerialNo(serialReturn);
        }
        if (graphics_card.isChecked()) {
            currentPositionHeight = currentPositionHeight + 20;
            graphicsCardText = graphics_card_name.getText().toString();
            graphicsCardPrice = Integer.parseInt(graphics_card_price.getText().toString());
            if (graphics_card_description.getText().toString().equals(null)) {
                graphics_card_description.setText("");
            } else {
                graphics_cardDescription = graphics_card_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//205
            canvas.drawText("Graphics Card", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(graphicsCardText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(graphics_cardDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS. " + String.valueOf(graphicsCardPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (ssd.isChecked()) {
            currentPositionHeight = currentPositionHeight + 20;
            ssdText = ssd_name.getText().toString();
            ssdPrice = Integer.parseInt(ssd_price.getText().toString());
            if (ssd_description.getText().toString().equals(null)) {
                ssd_description.setText("");
            } else {
                ssdDescription = ssd_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//225
            canvas.drawText("SSD", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(ssdText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(ssdDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS. " + String.valueOf(ssdPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (hdd.isChecked()) {
            currentPositionHeight = currentPositionHeight + 20;
            hddText = hdd_name.getText().toString();
            hddPrice = Integer.parseInt(hdd_price.getText().toString());
            if (hdd_description.getText().toString().equals(null)) {
                hdd_description.setText("");
            } else {
                hddDescription = hdd_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//245
            canvas.drawText("HDD", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(hddText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(hddDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS. " + String.valueOf(hddPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (power_supply.isChecked()) {
            currentPositionHeight = currentPositionHeight + 20;
            powerSupplyText = power_supply_name.getText().toString();
            powerSupplyPrice = Integer.parseInt(power_supply_price.getText().toString());
            if (power_supply_description.getText().toString().equals(null)) {
                power_supply_description.setText("");
            } else {
                power_supplyDescription = power_supply_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//265
            canvas.drawText("Power Supply", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(powerSupplyText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(power_supplyDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS. " + String.valueOf(powerSupplyPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (headset.isChecked()) {
            currentPositionHeight = currentPositionHeight + 20;
            headsetText = headset_name.getText().toString();
            headsetPrice = Integer.parseInt(headset_price.getText().toString());
            if (headset_description.getText().toString().equals(null)) {
                headset_description.setText("");
            } else {
                headsetDescription = headset_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//285
            canvas.drawText("Headset", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(headsetText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(headsetDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS. " + String.valueOf(headsetPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (keyboard.isChecked()) {
            currentPositionHeight = currentPositionHeight + 20;
            keyboardText = keyboard_name.getText().toString();
            keyboardPrice = Integer.parseInt(keyboard_price.getText().toString());
            if (keyboard_description.getText().toString().equals(null)) {
                keyboard_description.setText("");
            } else {
                keyboardDescription = keyboard_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//305
            canvas.drawText("Keyboard", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(keyboardText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(keyboardDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS. " + String.valueOf(keyboardPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (mouse.isChecked()) {
            currentPositionHeight = currentPositionHeight + 20;
            mouseText = mouse_name.getText().toString();
            mousePrice = Integer.parseInt(mouse_price.getText().toString());
            if (mouse_description.getText().toString().equals(null)) {
                mouse_description.setText("");
            } else {
                mouseDescription = mouse_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//325
            canvas.drawText("Mouse", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(mouseText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(mouseDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS. " + String.valueOf(mousePrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (cabinet.isChecked()) {
            currentPositionHeight = currentPositionHeight + 20;
            cabinetText = cabinet_name.getText().toString();
            cabinetPrice = Integer.parseInt(cabinet_price.getText().toString());
            if (cabinet_description.getText().toString().equals(null)) {
                cabinet_description.setText("");
            } else {
                cabinetDescription = cabinet_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//345
            canvas.drawText("Cabinet", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(cabinetText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(cabinetDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS. " + String.valueOf(cabinetPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (monitor.isChecked()) {
            currentPositionHeight = currentPositionHeight + 20;
            monitorText = monitor_name.getText().toString();
            monitorPrice = Integer.parseInt(monitor_price.getText().toString());
            if (monitor_description.getText().toString().equals(null)) {
                monitor_description.setText("");
            } else {
                monitorDescription = monitor_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//365
            canvas.drawText("Monitor", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(monitorText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(monitorDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS. " + String.valueOf(monitorPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (case_fans.isChecked()) {
            currentPositionHeight = currentPositionHeight + 20;
            caseFansText = case_fans_name.getText().toString();
            caseFansPrice = Integer.parseInt(case_fans_price.getText().toString());
            if (case_fans_description.getText().toString().equals(null)) {
                case_fans_description.setText("");
            } else {
                case_fansDescription = case_fans_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//385
            canvas.drawText("Case Fans", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(caseFansText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(case_fansDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS. " + String.valueOf(caseFansPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (cable.isChecked()) {
            currentPositionHeight = currentPositionHeight + 20;
            cableText = cable_name.getText().toString();
            cablePrice = Integer.parseInt(cable_price.getText().toString());
            if (cable_description.getText().toString().equals(null)) {
                cable_description.setText("");
            } else {
                cableDescription = cable_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//405
            canvas.drawText("Cable", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(cableText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(cableDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS. " + String.valueOf(cablePrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (service.isChecked()) {
            int temp = currentPositionHeight + 20;
            currentPositionHeight = temp;
            serviceText = service_name.getText().toString();
            servicePrice = Integer.parseInt(service_price.getText().toString());
            if (service_description.getText().toString().equals(null)) {
                service_description.setText("");
            } else {
                serviceDescription = service_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//425
            canvas.drawText("Service", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(serviceText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(serviceDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS. " + String.valueOf(servicePrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (cooler.isChecked()) {
            currentPositionHeight = currentPositionHeight + 20;
            coolerText = cooler_name.getText().toString();
            coolerPrice = Integer.parseInt(cooler_price.getText().toString());
            if (cooler_description.getText().toString().equals(null)) {
                cooler_description.setText("");
            } else {
                coolerDescription = cooler_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint); // 445
            canvas.drawText("Cooler", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(coolerText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(coolerDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS. " + String.valueOf(coolerPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            updateSerialNo(serialReturn);
        }

        totalAmount = processorPrice + motherboardPrice + graphicsCardPrice + ramPrice + ssdPrice
                + hddPrice + powerSupplyPrice + coolerPrice + cabinetPrice + coolerPrice
                + servicePrice + caseFansPrice + keyboardPrice + mousePrice + headsetPrice;

        paint.setColor(Color.rgb(150, 150, 150));
        currentPositionHeight = currentPositionHeight + 20;
        canvas.drawRect(currentPageWidth + 25, currentPageHeight + currentPositionHeight - 2, totalPageWidth - 25, currentPageHeight + currentPositionHeight, paint);

        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        currentPositionHeight = currentPositionHeight + 20;
        canvas.drawText("", currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);
        canvas.drawText("TOTAL AMOUNT", currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
        canvas.drawText("", currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
        canvas.drawText("RS. " + String.valueOf(totalAmount), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);

        // draw vertical line TODO: need to change the value of height according to list
        currentPositionHeight = currentPositionHeight + 10; // get current position
        paint.setColor(Color.rgb(150, 150, 150));
        canvas.drawRect(currentPageWidth + 25, currentPageHeight + 170, currentPageWidth + 27, currentPageHeight + currentPositionHeight, paint);
        canvas.drawRect(currentPageWidth + 70, currentPageHeight + 170, currentPageWidth + 72, currentPageHeight + currentPositionHeight, paint);
        canvas.drawRect(currentPageWidth + 145, currentPageHeight + 170, currentPageWidth + 147, currentPageHeight + currentPositionHeight, paint);
        canvas.drawRect(currentPageWidth + 250, currentPageHeight + 170, currentPageWidth + 252, currentPageHeight + currentPositionHeight, paint);
        canvas.drawRect(currentPageWidth + 510, currentPageHeight + 170, currentPageWidth + 512, currentPageHeight + currentPositionHeight, paint);
        canvas.drawRect(currentPageWidth + 568, currentPageHeight + 170, currentPageWidth + 570, currentPageHeight + currentPositionHeight, paint);

        // end of table
        canvas.drawRect(currentPageWidth + 25, currentPageHeight + currentPositionHeight, totalPageWidth - 25, currentPageHeight + currentPositionHeight + 2, paint);
/*
        //TODO: INSERT TO DATABASE
        convertQuotationToPdfHelperSql.insertMain(dateFormat.format(date.getTime()), numberText, processorText, processorPrice, processorDescription,
                motherboardText, motherboardPrice, motherboardDescription, ramText, ramPrice, ramDescription, graphicsCardText, graphicsCardPrice, graphics_cardDescription,
                ssdText, ssdPrice,ssdDescription,totalAmount);
*/

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setColor(Color.BLACK);
        paint.setTextSize(10);
        canvas.drawText(warrantyHeading, currentPageWidth + 20, totalPageHeight - 200, paint);
        canvas.drawText(warranty1, currentPageWidth + 40, totalPageHeight - 185, paint);
        canvas.drawText(warranty2, currentPageWidth + 45, totalPageHeight - 170, paint);
        canvas.drawText(warranty3, currentPageWidth + 40, totalPageHeight - 155, paint);
        canvas.drawText(warranty4, currentPageWidth + 40, totalPageHeight - 140, paint);

        canvas.drawText(policyHeading, currentPageWidth + 20, totalPageHeight - 120, paint);
        canvas.drawText(policy1, currentPageWidth + 40, totalPageHeight - 105, paint);
        canvas.drawText(policy2, currentPageWidth + 40, totalPageHeight - 90, paint);
        canvas.drawText(policy3, currentPageWidth + 40, totalPageHeight - 75, paint);
        canvas.drawText(policy4, currentPageWidth + 40, totalPageHeight - 60, paint);
        canvas.drawText(policy5, currentPageWidth + 45, totalPageHeight - 45, paint);
        canvas.drawText(policy6, currentPageWidth + 40, totalPageHeight - 30, paint);
        pdfDocument.finishPage(page);

        //display total value on screen
        //Toast.makeText(Convert_Quotation_To_PDFSQLActivity.this, "Total Value is : RS.  " +totalAmount, Toast.LENGTH_LONG).show();

        //File file = new File(this.getExternalFilesDir("/"), cursor.getInt(0) + "_CustomBuilds.pdf");
        //File file = new File(this.getExternalFilesDir("/"), billName+"/"+ customerMobileNumber + "/" + dateFormat.format(date.getTime()) + "CustomBuilds.pdf");
        File file = new File(this.getExternalFilesDir("/"), billName+"/"+ "CustomBuilds.pdf");

        try {

            pdfDocument.writeTo(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfDocument.close();
        Toast.makeText(Convert_Quotation_To_PDFSQLActivity.this, "Successfully converted to PDF", Toast.LENGTH_LONG).show();

    }

    private int updateSerialNo(int serialNo) {
        return serialReturn = serialNo + 1;
    }

   // public void printOld(View view) {}

}