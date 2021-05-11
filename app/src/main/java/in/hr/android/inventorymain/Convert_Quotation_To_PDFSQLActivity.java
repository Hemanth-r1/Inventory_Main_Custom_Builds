package in.hr.android.inventorymain;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
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
    String billName, numberText;

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

    String nameText, processorText, motherboardText, ramText, graphicsCardText,
            ssdText, hddText, powerSupplyText, headsetText, keyboardText,
            mouseText, cabinetText, monitorText, caseFansText, cableText, serviceText, coolerText;

    String processorDescription, motherboardDescription, ramDescription, graphics_cardDescription,
            power_supplyDescription, ssdDescription, hddDescription, coolerDescription, cabinetDescription,
            case_fansDescription, keyboardDescription,
            mouseDescription, monitorDescription, serviceDescription, cableDescription, headsetDescription;


    int processorPrice;
    int motherboardPrice;
    int ramPrice;
    int graphicsCardPrice;
    int ssdPrice;
    int hddPrice;
    int powerSupplyPrice;
    int headsetPrice;
    int keyboardPrice;
    int mousePrice;
    int cabinetPrice;
    int monitorPrice;
    int caseFansPrice;
    int cablePrice;
    int servicePrice;
    int coolerPrice;

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

        convertQuotationToPdfHelperSql = new Convert_Quotation_To_PDF_Helper_SQL(this);
        sqLiteDatabase = convertQuotationToPdfHelperSql.getWritableDatabase();
    }

    private void callFindViewByID() {

        customerBillName = findViewById(R.id.editTextBillName);
        //customerMobileNumber = findViewById(R.id.editTextCustomerNumber);

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

        cable = findViewById(R.id.checkBoxCable);
        cable_name = findViewById(R.id.editTextCableName);
        cable_price = findViewById(R.id.editTextCablePrice);
        cable_description = findViewById(R.id.cableDescription);

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
        int currentPositionHeight ;

        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();

        billName = customerBillName.getText().toString();
        // numberText = customerMobileNumber.getText().toString();

        /*
        // data to be retrieved
        String[] column = {"date","nameText", "numberText" , "processorText",
                "processorPrice", "processorDescription",  "motherboardText", "motherboardPrice", "motherboardDescription",
                "ramText", "ramPrice", "ramDescription", "graphicsCardText", "graphicsCardPrice","graphics_cardDescription" ,
                "ssdText", "ssdPrice", "ssdDescription", "amount" };

        Cursor cursor = sqLiteDatabase.query("QuoteTABLEMain", column, null, null, null, null, null);
        cursor.move(cursor.getCount());

            /*
             for A4 size sheet width = 20.98 and height = 29.66, builder below used POST SCRIPT as unit
             which in turn is defined as 1/72 inch
            so divide the STANDARD WIDTH and HEIGHT in CM by 2.54 [result inch]
            multiply the value by 72 to get POST SCRIPT value
            Width = 20.98 cm for A4
            Height  = 29.66 cm for A4
            POSTSCRIPT width =  20.98 / 2.54 = 8.26 => 8.26 * 72 = 594.70 => 595 int
            POSTSCRIPT height = 29.66 / 2.54 = 11.67 => 11.67 * 72 = 840.75 => 841 int
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
        canvas.drawText("Custom Builds", currentPageWidth + 250, currentPageHeight + 25, paint);

        //second text
        paint.setTextSize(8);
        canvas.drawText("#21, 1st Main, Rajeev Gandhi Nagar, Nandini Layout, Bengaluru 96", currentPageWidth + 200, currentPageHeight + 35, paint);

        canvas.drawText("Quotation Number", totalPageWidth - 120, currentPageHeight + 30, paint);
        canvas.drawText(String.valueOf(quoteNo), totalPageWidth - 120, currentPageHeight + 30, paint);

        canvas.drawRect(currentPageWidth + 10, currentPageHeight + 38, totalPageWidth - 10, currentPageHeight + 40, paint);

        canvas.drawText("Date :", currentPageWidth + 40, currentPageHeight + 50, paint);
        canvas.drawText(dateFormat.format(date.getTime()), 70, 50, paint);

        canvas.drawText("Time :", currentPageWidth + 150, currentPageHeight + 50, paint);
        //canvas.drawText(timeFormat.format(cursor.getLong(3)), canvas.getWidth() - 40, 200, paint);
        canvas.drawText(timeFormat.format(date.getTime()), 180, 50, paint);

        paint.setTextSize(10);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("JUST A QUOTE", currentPageWidth + 250, currentPageHeight + 50, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // billing from
        paint.setTextSize(8);
        paint.setColor(Color.rgb(150, 150, 150));
        canvas.drawRect(currentPageWidth + 15, currentPageHeight + 55, currentPageWidth + 40, currentPageHeight + 62, paint);

        paint.setColor(Color.WHITE);
        canvas.drawText("FROM", currentPageWidth + 17, currentPageHeight + 62, paint);

        paint.setColor(Color.BLACK);
        canvas.drawText("KIRAN R ", currentPageWidth + 20, currentPageHeight + 70, paint);
        canvas.drawText("CUSTOM BUILDS TECHNOLOGY", currentPageWidth + 20, currentPageHeight + 78, paint);
        canvas.drawText("NANDINI LAYOUT, BENGALURU 96", currentPageWidth + 20, currentPageHeight + 86, paint);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("MOBILE NUMBER: 9739942971", currentPageWidth + 20, currentPageHeight + 95, paint);

        canvas.drawText("GST IN: 29JVVPK7688R1ZL", totalPageWidth - 150, currentPageHeight + 78, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // draw divider line
        canvas.drawRect(currentPageWidth + 10, currentPageHeight + 100, totalPageWidth - 10, currentPageHeight + 102, paint);

        // create rectangular bar
        paint.setColor(Color.rgb(150, 150, 150));
        canvas.drawRect(currentPageWidth + 25, currentPageHeight + 110, totalPageWidth - 25, currentPageHeight + 130, paint);

        // draw vertical line TODO: need to change the value of height according to list
        paint.setColor(Color.rgb(150, 150, 150));
        canvas.drawRect(currentPageWidth + 25, currentPageHeight + 130, currentPageWidth + 27, currentPageHeight + 502, paint);
        canvas.drawRect(currentPageWidth + 70, currentPageHeight + 130, currentPageWidth + 72, currentPageHeight + 502, paint);
        canvas.drawRect(currentPageWidth + 165, currentPageHeight + 130, currentPageWidth + 167, currentPageHeight + 502, paint);
        canvas.drawRect(currentPageWidth + 300, currentPageHeight + 130, currentPageWidth + 302, currentPageHeight + 502, paint);
        canvas.drawRect(currentPageWidth + 520, currentPageHeight + 130, currentPageWidth + 522, currentPageHeight + 502, paint);
        canvas.drawRect(currentPageWidth + 567, currentPageHeight + 130, currentPageWidth + 569, currentPageHeight + 502, paint);

        // end of table
        canvas.drawRect(currentPageWidth + 25, currentPageHeight + 500, totalPageWidth - 25, currentPageHeight + 502, paint);


        //table heading
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setColor(Color.WHITE);
        canvas.drawText("Sl no", currentPageWidth + 45, currentPageHeight + 125, paint);
        canvas.drawText("Part", currentPageWidth + 95, currentPageHeight + 125, paint);
        canvas.drawText("Brand", currentPageWidth + 195, currentPageHeight + 125, paint);
        canvas.drawText("Description", currentPageWidth + 335, currentPageHeight + 125, paint);
        canvas.drawText("Amount", currentPageWidth + 525, currentPageHeight + 125, paint);


        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setColor(Color.BLACK);

        currentPositionHeight = 145;

        if (processor.isChecked()) {
            processorText = processor_name.getText().toString();
            processorPrice = Integer.parseInt(processor_price.getText().toString());
            if (processor_description.getText().toString().equals("")) {
                processor_description.setText("");
            } else {
                processorDescription = processor_description.getText().toString();
            }

            canvas.drawText(String.valueOf(serialNo), currentPageWidth + 55, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("Processor", currentPageWidth + 95, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(processorText, currentPageWidth + 195, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(processorDescription, currentPageWidth + 335, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS." + String.valueOf(processorPrice), currentPageWidth + 525, currentPageHeight + currentPositionHeight, paint);
            // canvas.drawRect(currentPageWidth + 25, currentPageHeight + 155, totalPageWidth - 25, currentPageHeight +156, paint);
            updateSerialNo(serialNo);
        }
        if (motherboard.isChecked()) {

            //TODO: check
            currentPositionHeight = currentPositionHeight + 20;
            motherboardText = motherboard_name.getText().toString();
            motherboardPrice = Integer.parseInt(motherboard_price.getText().toString());
            if (motherboard_description.getText().toString().equals("")) {
                motherboard_description.setText("");
            } else {
                motherboardDescription = motherboard_description.getText().toString();
            }

            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + 55, currentPageHeight + currentPositionHeight, paint);//165
            canvas.drawText("Motherboard", currentPageWidth + 95, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(motherboardText, currentPageWidth + 195, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(motherboardDescription, currentPageWidth + 335, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS." + String.valueOf(motherboardPrice), currentPageWidth + 525, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }

        if (ram.isChecked()) {
            int temp = currentPositionHeight + 20;
            currentPositionHeight = temp;
            ramText = ram_name.getText().toString();
            ramPrice = Integer.parseInt(ram_price.getText().toString());
            if (ram_description.getText().toString().equals("")) {
                ram_description.setText("");
            } else {
                ramDescription = ram_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + 55, currentPageHeight + currentPositionHeight, paint); //185
            canvas.drawText("Ram", currentPageWidth + 95, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(ramText, currentPageWidth + 195, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(ramDescription, currentPageWidth + 335, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS." + String.valueOf(ramPrice), currentPageWidth + 525, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint); +185
            updateSerialNo(serialReturn);
        }
        if (graphics_card.isChecked()) {
            int temp = currentPositionHeight + 20;
            currentPositionHeight = temp;
            graphicsCardText = graphics_card_name.getText().toString();
            graphicsCardPrice = Integer.parseInt(graphics_card_price.getText().toString());
            if (graphics_card_description.getText().toString().equals("")) {
                graphics_card_description.setText("");
            } else {
                graphics_cardDescription = graphics_card_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + 55, currentPageHeight + currentPositionHeight, paint);//205
            canvas.drawText("Graphics Card", currentPageWidth + 95, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(graphicsCardText, currentPageWidth + 195, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(graphics_cardDescription, currentPageWidth + 335, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS." + String.valueOf(graphicsCardPrice), currentPageWidth + 525, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (ssd.isChecked()) {
            int temp = currentPositionHeight + 20;
            currentPositionHeight = temp;
            ssdText = ssd_name.getText().toString();
            ssdPrice = Integer.parseInt(ssd_price.getText().toString());
            if (ssd_description.getText().toString().equals("")) {
                ssd_description.setText("");
            } else {
                ssdDescription = ssd_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + 55, currentPageHeight + currentPositionHeight, paint);//225
            canvas.drawText("SSD", currentPageWidth + 95, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(ssdText, currentPageWidth + 195, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(ssdDescription, currentPageWidth + 335, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS." + String.valueOf(ssdPrice), currentPageWidth + 525, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (hdd.isChecked()) {
            int temp = currentPositionHeight + 20;
            currentPositionHeight = temp;
            hddText = hdd_name.getText().toString();
            hddPrice = Integer.parseInt(hdd_price.getText().toString());
            if (hdd_description.getText().toString().equals("")) {
                hdd_description.setText("");
            } else {
                hddDescription = hdd_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + 55, currentPageHeight + currentPositionHeight, paint);//245
            canvas.drawText("HDD", currentPageWidth + 95, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(hddText, currentPageWidth + 195, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(hddDescription, currentPageWidth + 335, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS." + String.valueOf(hddPrice), currentPageWidth + 525, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (power_supply.isChecked()) {
            int temp = currentPositionHeight + 20;
            currentPositionHeight = temp;
            powerSupplyText = power_supply_name.getText().toString();
            powerSupplyPrice = Integer.parseInt(power_supply_price.getText().toString());
            if (power_supply_description.getText().toString().equals("")) {
                power_supply_description.setText("");
            } else {
                power_supplyDescription = power_supply_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + 55, currentPageHeight + currentPositionHeight, paint);//265
            canvas.drawText("Power Supply", currentPageWidth + 95, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(powerSupplyText, currentPageWidth + 195, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(power_supplyDescription, currentPageWidth + 335, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS." + String.valueOf(powerSupplyPrice), currentPageWidth + 525, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (headset.isChecked()) {
            int temp = currentPositionHeight + 20;
            currentPositionHeight = temp;
            headsetText = headset_name.getText().toString();
            headsetPrice = Integer.parseInt(headset_price.getText().toString());
            if (headset_description.getText().toString().equals("")) {
                headset_description.setText("");
            } else {
                headsetDescription = headset_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + 55, currentPageHeight + currentPositionHeight, paint);//285
            canvas.drawText("Headset", currentPageWidth + 95, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(headsetText, currentPageWidth + 195, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(headsetDescription, currentPageWidth + 335, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS." + String.valueOf(headsetPrice), currentPageWidth + 525, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (keyboard.isChecked()) {
            int temp = currentPositionHeight + 20;
            currentPositionHeight = temp;
            keyboardText = keyboard_name.getText().toString();
            keyboardPrice = Integer.parseInt(keyboard_price.getText().toString());
            if (keyboard_description.getText().toString().equals("")) {
                keyboard_description.setText("");
            } else {
                keyboardDescription = keyboard_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + 55, currentPageHeight + currentPositionHeight, paint);//305
            canvas.drawText("Keyboard", currentPageWidth + 95, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(keyboardText, currentPageWidth + 195, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(keyboardDescription, currentPageWidth + 335, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS." + String.valueOf(keyboardPrice), currentPageWidth + 525, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (mouse.isChecked()) {
            int temp = currentPositionHeight + 20;
            currentPositionHeight = temp;
            mouseText = mouse_name.getText().toString();
            mousePrice = Integer.parseInt(mouse_price.getText().toString());
            if (mouse_description.getText().toString().equals("")) {
                mouse_description.setText("");
            } else {
                mouseDescription = mouse_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + 55, currentPageHeight + currentPositionHeight, paint);//325
            canvas.drawText("Mouse", currentPageWidth + 95, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(mouseText, currentPageWidth + 195, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(mouseDescription, currentPageWidth + 335, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS." + String.valueOf(mousePrice), currentPageWidth + 525, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (cabinet.isChecked()) {
            int temp = currentPositionHeight + 20;
            currentPositionHeight = temp;
            cabinetText = cabinet_name.getText().toString();
            cabinetPrice = Integer.parseInt(cabinet_price.getText().toString());
            if (cabinet_description.getText().toString().equals("")) {
                cabinet_description.setText("");
            } else {
                cabinetDescription = cabinet_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + 55, currentPageHeight + currentPositionHeight, paint);//345
            canvas.drawText("Cabinet", currentPageWidth + 95, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(cabinetText, currentPageWidth + 195, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(cabinetDescription, currentPageWidth + 335, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS." + String.valueOf(cabinetPrice), currentPageWidth + 525, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (monitor.isChecked()) {
            int temp = currentPositionHeight + 20;
            currentPositionHeight = temp;
            monitorText = monitor_name.getText().toString();
            monitorPrice = Integer.parseInt(monitor_price.getText().toString());
            if (monitor_description.getText().toString().equals("")) {
                monitor_description.setText("");
            } else {
                monitorDescription = monitor_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + 55, currentPageHeight + currentPositionHeight, paint);//365
            canvas.drawText("Monitor", currentPageWidth + 95, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(monitorText, currentPageWidth + 195, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(monitorDescription, currentPageWidth + 335, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS." + String.valueOf(monitorPrice), currentPageWidth + 525, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (case_fans.isChecked()) {
            int temp = currentPositionHeight + 20;
            currentPositionHeight = temp;
            caseFansText = case_fans_name.getText().toString();
            caseFansPrice = Integer.parseInt(case_fans_price.getText().toString());
            if (case_fans_description.getText().toString().equals("")) {
                case_fans_description.setText("");
            } else {
                case_fansDescription = case_fans_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + 55, currentPageHeight + currentPositionHeight, paint);//385
            canvas.drawText("Case Fans", currentPageWidth + 95, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(caseFansText, currentPageWidth + 195, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(case_fansDescription, currentPageWidth + 335, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS." + String.valueOf(caseFansPrice), currentPageWidth + 525, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (cable.isChecked()) {
            int temp = currentPositionHeight + 20;
            currentPositionHeight = temp;
            cableText = cable_name.getText().toString();
            cablePrice = Integer.parseInt(cable_price.getText().toString());
            if (cable_description.getText().toString().equals("")) {
                cable_description.setText("");
            } else {
                cableDescription = cable_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + 55, currentPageHeight + currentPositionHeight, paint);//405
            canvas.drawText("Cable", currentPageWidth + 95, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(cableText, currentPageWidth + 195, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(cableDescription, currentPageWidth + 335, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS." + String.valueOf(cablePrice), currentPageWidth + 525, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (service.isChecked()) {
            int temp = currentPositionHeight + 20;
            currentPositionHeight = temp;
            serviceText = service_name.getText().toString();
            servicePrice = Integer.parseInt(service_price.getText().toString());
            if (service_description.getText().toString().equals("")) {
                service_description.setText("");
            } else {
                serviceDescription = service_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + 55, currentPageHeight + currentPositionHeight, paint);//425
            canvas.drawText("Service", currentPageWidth + 95, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(serviceText, currentPageWidth + 195, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(serviceDescription, currentPageWidth + 335, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS." + String.valueOf(servicePrice), currentPageWidth + 525, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        if (cooler.isChecked()) {
            int temp = currentPositionHeight + 20;
            currentPositionHeight = temp;
            coolerText = cooler_name.getText().toString();
            coolerPrice = Integer.parseInt(cooler_price.getText().toString());
            if (cooler_description.getText().toString().equals("")) {
                cooler_description.setText("");
            } else {
                coolerDescription = cooler_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + 55, currentPageHeight + currentPositionHeight, paint); // 445
            canvas.drawText("Cooler", currentPageWidth + 95, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(coolerText, currentPageWidth + 195, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(coolerDescription, currentPageWidth + 335, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("RS." + String.valueOf(coolerPrice), currentPageWidth + 525, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            updateSerialNo(serialReturn);
        }
        /*
        canvas.drawText(cursor.getString(4), 35, 280, paint);
        canvas.drawText(String.valueOf(cursor.getInt(5)), 100, 280, paint);
        canvas.drawText(String.valueOf(cursor.getInt(6)), 190, 280, paint);

        paint.setColor(Color.rgb(150, 150, 150));
        canvas.drawRect(30, 290, canvas.getWidth() - 40, 295, paint);

        paint.setColor(Color.BLACK);
        canvas.drawText("Sub Total", 100, 310, paint);
        canvas.drawText("Tax 4%", 100, 320, paint);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("TOTAL", 100, 335, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));


        canvas.drawText(String.valueOf(cursor.getInt(6)), 265, 350, paint);
        canvas.drawText(String.valueOf(cursor.getInt(6) * 4 / 100), 265, 370, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText(String.valueOf(cursor.getInt(6) + (cursor.getInt(6) * 4 / 100)), 265, 390, paint);

        canvas.drawText("Make all check payable to \"Custom Builds\"", 50, 500, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        canvas.drawText("Thank you very much", 50, 520, paint);
        pdfDocument.finishPage(page);
         */

        totalAmount = processorPrice + motherboardPrice + graphicsCardPrice + ramPrice + ssdPrice
                + hddPrice + powerSupplyPrice + coolerPrice + cabinetPrice + coolerPrice
                + servicePrice + caseFansPrice + keyboardPrice + mousePrice + headsetPrice;

        canvas.drawRect(currentPageWidth + 25, currentPageHeight + 479, totalPageWidth - 25, currentPageHeight + 480, paint);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        canvas.drawText("", currentPageWidth + 95, currentPageHeight + 490, paint);
        canvas.drawText("TOTAL AMOUNT", currentPageWidth + 195, currentPageHeight + 490, paint);
        canvas.drawText("", currentPageWidth + 335, currentPageHeight + 490, paint);
        canvas.drawText("RS." + String.valueOf(totalAmount), currentPageWidth + 525, currentPageHeight + 490, paint);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

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


        //File file = new File(this.getExternalFilesDir("/"), cursor.getInt(0) + "_CustomBuilds.pdf");
        File file = new File(this.getExternalFilesDir("/"), billName + "_CustomBuilds.pdf");
        quoteNo++;

        try {
            pdfDocument.finishPage(page);
            pdfDocument.writeTo(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfDocument.close();

        Toast.makeText(Convert_Quotation_To_PDFSQLActivity.this, "Successfully converted to PDF", Toast.LENGTH_LONG).show();
    }

  /*  {

        /*
        if (!customerName.getText().toString().equals("")) {
            nameText = customerName.getText().toString();
        }else{
            Toast.makeText(Convert_Quotation_To_PDFSQLActivity.this, "Fill Customer Details", Toast.LENGTH_LONG).show();
        }

        if (!customerMobileNumber.getText().toString().equals("")) {
            numberText = Integer.parseInt(customerMobileNumber.getText().toString());
        }else {
            Toast.makeText(Convert_Quotation_To_PDFSQLActivity.this, "Fill Customer Details", Toast.LENGTH_LONG).show();
        }

         */
    /*
        if (processor.isChecked()) {
            processorText = processor_name.getText().toString();
            processorPrice = Integer.parseInt(processor_price.getText().toString());
            if (processor_description.getText().toString().equals("")) {
                processor_description.setText("");
            } else {
                processorDescription = processor_description.getText().toString();
            }
        }

        //TODO:
        if (motherboard.isChecked()) {
            motherboardText = motherboard_name.getText().toString();
            motherboardPrice = Integer.parseInt(motherboard_price.getText().toString());
            if (motherboard_description.getText().toString().equals("")) {
                motherboard_description.setText("");
            } else {
                motherboardDescription = motherboard_description.getText().toString();
            }
        }
        if (ram.isChecked()) {
            ramText = ram_name.getText().toString();
            ramPrice = Integer.parseInt(ram_price.getText().toString());
            if (ram_description.getText().toString().equals("")) {
                ram_description.setText("");
            } else {
                ramDescription = ram_description.getText().toString();
            }
        }
        if (graphics_card.isChecked()) {
            graphicsCardText = graphics_card_name.getText().toString();
            graphicsCardPrice = Integer.parseInt(graphics_card_price.getText().toString());
            if (graphics_card_description.getText().toString().equals("")) {
                graphics_card_description.setText("");
            } else {
                graphics_cardDescription = graphics_card_description.getText().toString();
            }
        }
        if (ssd.isChecked()) {
            ssdText = ssd_name.getText().toString();
            ssdPrice = Integer.parseInt(ssd_price.getText().toString());
            if (ssd_description.getText().toString().equals("")) {
                ssd_description.setText("");
            } else {
                ssdDescription = ssd_description.getText().toString();
            }
        }
        if (hdd.isChecked()) {
            hddText = hdd_name.getText().toString();
            hddPrice = Integer.parseInt(hdd_price.getText().toString());
            if (hdd_description.getText().toString().equals("")) {
                hdd_description.setText("");
            } else {
                hddDescription = hdd_description.getText().toString();
            }
        }
        if (power_supply.isChecked()) {
            powerSupplyText = power_supply_name.getText().toString();
            powerSupplyPrice = Integer.parseInt(power_supply_price.getText().toString());
            if (power_supply_description.getText().toString().equals("")) {
                power_supply_description.setText("");
            } else {
                power_supplyDescription = power_supply_description.getText().toString();
            }
        }
        if (headset.isChecked()) {
            headsetText = headset_name.getText().toString();
            headsetPrice = Integer.parseInt(headset_price.getText().toString());
            if (headset_description.getText().toString().equals("")) {
                headset_description.setText("");
            } else {
                headsetDescription = headset_description.getText().toString();
            }
        }
        if (keyboard.isChecked()) {
            keyboardText = keyboard_name.getText().toString();
            keyboardPrice = Integer.parseInt(keyboard_price.getText().toString());
            if (keyboard_description.getText().toString().equals("")) {
                keyboard_description.setText("");
            } else {
                keyboardDescription = keyboard_description.getText().toString();
            }
        }
        if (mouse.isChecked()) {
            mouseText = mouse_name.getText().toString();
            mousePrice = Integer.parseInt(mouse_price.getText().toString());
            if (mouse_description.getText().toString().equals("")) {
                mouse_description.setText("");
            } else {
                mouseDescription = mouse_description.getText().toString();
            }
        }
        if (cabinet.isChecked()) {
            cabinetText = cabinet_name.getText().toString();
            cabinetPrice = Integer.parseInt(cabinet_price.getText().toString());
            if (cabinet_description.getText().toString().equals("")) {
                cabinet_description.setText("");
            } else {
                cabinetDescription = cabinet_description.getText().toString();
            }
        }
        if (monitor.isChecked()) {
            monitorText = monitor_name.getText().toString();
            monitorPrice = Integer.parseInt(monitor_price.getText().toString());
            if (monitor_description.getText().toString().equals("")) {
                monitor_description.setText("");
            } else {
                monitorDescription = monitor_description.getText().toString();
            }
        }
        if (case_fans.isChecked()) {
            caseFansText = case_fans_name.getText().toString();
            caseFansPrice = Integer.parseInt(case_fans_price.getText().toString());
            if (case_fans_description.getText().toString().equals("")) {
                case_fans_description.setText("");
            } else {
                case_fansDescription = case_fans_description.getText().toString();
            }
        }
        if (cable.isChecked()) {
            cableText = cable_name.getText().toString();
            cablePrice = Integer.parseInt(cable_price.getText().toString());
            if (cable_description.getText().toString().equals("")) {
                cable_description.setText("");
            } else {
                cableDescription = cable_description.getText().toString();
            }
        }
        if (service.isChecked()) {
            serviceText = service_name.getText().toString();
            servicePrice = Integer.parseInt(service_price.getText().toString());
            if (service_description.getText().toString().equals("")) {
                service_description.setText("");
            } else {
                serviceDescription = service_description.getText().toString();
            }
        }
        if (power_supply.isChecked()) {
            coolerText = cooler_name.getText().toString();
            coolerPrice = Integer.parseInt(cooler_price.getText().toString());
            if (cooler_description.getText().toString().equals("")) {
                cooler_description.setText("");
            } else {
                coolerDescription = cooler_description.getText().toString();
            }
        }

        totalAmount = processorPrice + motherboardPrice + graphicsCardPrice + ramPrice + ssdPrice;

        /*
        convertQuotationToPdfHelperSql.insertMain(date.getTime(),nameText, numberText , processorText,
                processorPrice, processorDescription,  motherboardText, motherboardPrice, motherboardDescription,
                ramText, ramPrice, ramDescription, graphicsCardText, graphicsCardPrice,graphics_cardDescription ,
                ssdText, ssdPrice, ssdDescription, amount );


        printInvoice();
        Toast.makeText(Convert_Quotation_To_PDFSQLActivity.this, "Successfully Converted To PDF", Toast.LENGTH_LONG).show();
        }
     */

    private void printInvoice() {
        int serialNo = 0;

        // global page width and height
        int totalPageWidth = 595;
        int totalPageHeight = 841;

        int currentPageWidth = 0;
        int currentPageHeight = 0;

        int currentPositionWidth = 0;
        int currentPositionHeight = 0;

        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();

        /*
        // data to be retrieved
        String[] column = {"date","nameText", "numberText" , "processorText",
                "processorPrice", "processorDescription",  "motherboardText", "motherboardPrice", "motherboardDescription",
                "ramText", "ramPrice", "ramDescription", "graphicsCardText", "graphicsCardPrice","graphics_cardDescription" ,
                "ssdText", "ssdPrice", "ssdDescription", "amount" };

        Cursor cursor = sqLiteDatabase.query("QuoteTABLEMain", column, null, null, null, null, null);
        cursor.move(cursor.getCount());

            /*
             for A4 size sheet width = 20.98 and height = 29.66, builder below used POST SCRIPT as unit
             which in turn is defined as 1/72 inch
            so divide the STANDARD WIDTH and HEIGHT in CM by 2.54 [result inch]
            multiply the value by 72 to get POST SCRIPT value
            Width = 20.98 cm for A4
            Height  = 29.66 cm for A4
            POSTSCRIPT width =  20.98 / 2.54 = 8.26 => 8.26 * 72 = 594.70 => 595 int
            POSTSCRIPT height = 29.66 / 2.54 = 11.67 => 11.67 * 72 = 840.75 => 841 int
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
        canvas.drawText("Custom Builds", currentPageWidth + 250, currentPageHeight + 25, paint);

        //second text
        paint.setTextSize(8);
        canvas.drawText("#21, 1st Main, Rajeev Gandhi Nagar, Nandini Layout, Bengaluru 96", currentPageWidth + 200, currentPageHeight + 35, paint);

        canvas.drawText("Quotation Number", totalPageWidth - 120, currentPageHeight + 30, paint);
        canvas.drawText(String.valueOf(quoteNo), totalPageWidth - 120, currentPageHeight + 30, paint);

        /*
        canvas.drawText(String.valueOf(cursor.getInt(0)), canvas.getWidth() - 30, 30, paint);
        paint.setTextAlign(Paint.Align.LEFT);
        */

        canvas.drawRect(currentPageWidth + 10, currentPageHeight + 38, totalPageWidth - 10, currentPageHeight + 40, paint);

        canvas.drawText("Date :", currentPageWidth + 50, currentPageHeight + 50, paint);
        //canvas.drawText(dateFormat.format(cursor.getLong(3)), 70, 70, paint);

        canvas.drawText("Time :", currentPageWidth + 150, currentPageHeight + 50, paint);
        //canvas.drawText(timeFormat.format(cursor.getLong(3)), canvas.getWidth() - 40, 200, paint);
        //canvas.drawText(timeFormat.format(cursor.getLong(3)), 180, 70, paint);

        paint.setTextSize(10);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("JUST A QUOTE", currentPageWidth + 250, currentPageHeight + 50, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // billing from
        paint.setTextSize(8);
        paint.setColor(Color.rgb(150, 150, 150));
        canvas.drawRect(currentPageWidth + 15, currentPageHeight + 55, currentPageWidth + 40, currentPageHeight + 62, paint);

        paint.setColor(Color.WHITE);
        canvas.drawText("FROM", currentPageWidth + 17, currentPageHeight + 62, paint);

        paint.setColor(Color.BLACK);
        canvas.drawText("KIRAN R ", currentPageWidth + 20, currentPageHeight + 70, paint);
        canvas.drawText("CUSTOM BUILDS TECHNOLOGY", currentPageWidth + 20, currentPageHeight + 78, paint);
        canvas.drawText("NANDINI LAYOUT, BENGALURU 96", currentPageWidth + 20, currentPageHeight + 86, paint);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("MOBILE NUMBER: 9739942971", currentPageWidth + 20, currentPageHeight + 95, paint);

        canvas.drawText("GST IN: 29JVVPK7688R1ZL", totalPageWidth - 150, currentPageHeight + 78, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // draw divider line
        canvas.drawRect(currentPageWidth + 10, currentPageHeight + 100, totalPageWidth - 10, currentPageHeight + 102, paint);

        // create rectangular bar
        paint.setColor(Color.rgb(150, 150, 150));
        canvas.drawRect(currentPageWidth + 25, currentPageHeight + 110, totalPageWidth - 25, currentPageHeight + 130, paint);

        // draw vertical line TODO: need to change the value of height according to list
        paint.setColor(Color.rgb(150, 150, 150));
        canvas.drawRect(currentPageWidth + 25, currentPageHeight + 130, currentPageWidth + 27, currentPageHeight + 502, paint);
        canvas.drawRect(currentPageWidth + 70, currentPageHeight + 130, currentPageWidth + 72, currentPageHeight + 502, paint);
        canvas.drawRect(currentPageWidth + 165, currentPageHeight + 130, currentPageWidth + 167, currentPageHeight + 502, paint);
        canvas.drawRect(currentPageWidth + 300, currentPageHeight + 130, currentPageWidth + 302, currentPageHeight + 502, paint);
        canvas.drawRect(currentPageWidth + 520, currentPageHeight + 130, currentPageWidth + 522, currentPageHeight + 502, paint);
        canvas.drawRect(currentPageWidth + 567, currentPageHeight + 130, currentPageWidth + 569, currentPageHeight + 502, paint);
        // end of table
        canvas.drawRect(currentPageWidth + 25, currentPageHeight + 500, totalPageWidth - 25, currentPageHeight + 502, paint);
        //table heading
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setColor(Color.WHITE);
        canvas.drawText("Sl no", currentPageWidth + 45, currentPageHeight + 125, paint);
        canvas.drawText("Part", currentPageWidth + 95, currentPageHeight + 125, paint);
        canvas.drawText("Brand", currentPageWidth + 195, currentPageHeight + 125, paint);
        canvas.drawText("Description", currentPageWidth + 335, currentPageHeight + 125, paint);
        canvas.drawText("Amount", currentPageWidth + 525, currentPageHeight + 125, paint);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setColor(Color.BLACK);

        if (processor.isChecked()) {
            processorText = processor_name.getText().toString();
            processorPrice = Integer.parseInt(processor_price.getText().toString());
            if (processor_description.getText().toString().equals("")) {

                processor_description.setText("");
            } else {
                processorDescription = processor_description.getText().toString();
            }

            canvas.drawText(String.valueOf(serialNo + 1), currentPageWidth + 55, currentPageHeight + 145, paint);
            canvas.drawText("Processor", currentPageWidth + 95, currentPageHeight + 145, paint);
            canvas.drawText(processorText, currentPageWidth + 195, currentPageHeight + 145, paint);
            canvas.drawText(processorDescription, currentPageWidth + 335, currentPageHeight + 145, paint);
            canvas.drawText(String.valueOf(processorPrice), currentPageWidth + 525, currentPageHeight + 145, paint);
            // canvas.drawRect(currentPageWidth + 25, currentPageHeight + 155, totalPageWidth - 25, currentPageHeight +156, paint);
            updateSerialNo(serialNo);
        }
        if (motherboard.isChecked()) {
            motherboardText = motherboard_name.getText().toString();
            motherboardPrice = Integer.parseInt(motherboard_price.getText().toString());
            if (motherboard_description.getText().toString().equals("")) {
                motherboard_description.setText("");
            } else {
                motherboardDescription = motherboard_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialNo + 1), currentPageWidth + 55, currentPageHeight + 165, paint);
            canvas.drawText("Motherboard", currentPageWidth + 95, currentPageHeight + 165, paint);
            canvas.drawText(motherboardText, currentPageWidth + 195, currentPageHeight + 165, paint);
            canvas.drawText(motherboardDescription, currentPageWidth + 335, currentPageHeight + 165, paint);
            canvas.drawText(String.valueOf(motherboardPrice), currentPageWidth + 525, currentPageHeight + 165, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);

        }

        if (ram.isChecked()) {
            ramText = ram_name.getText().toString();
            ramPrice = Integer.parseInt(ram_price.getText().toString());
            if (ram_description.getText().toString().equals("")) {
                ram_description.setText("");
            } else {
                ramDescription = ram_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialNo + 1), currentPageWidth + 55, currentPageHeight + 185, paint);
            canvas.drawText("Ram", currentPageWidth + 95, currentPageHeight + 185, paint);
            canvas.drawText(ramText, currentPageWidth + 195, currentPageHeight + 185, paint);
            canvas.drawText(ramDescription, currentPageWidth + 335, currentPageHeight + 185, paint);
            canvas.drawText(String.valueOf(ramPrice), currentPageWidth + 525, currentPageHeight + 185, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);

        }
        if (graphics_card.isChecked()) {
            graphicsCardText = graphics_card_name.getText().toString();
            graphicsCardPrice = Integer.parseInt(graphics_card_price.getText().toString());
            if (graphics_card_description.getText().toString().equals("")) {
                graphics_card_description.setText("");
            } else {
                graphics_cardDescription = graphics_card_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialNo + 1), currentPageWidth + 55, currentPageHeight + 205, paint);
            canvas.drawText("Graphics Card", currentPageWidth + 95, currentPageHeight + 205, paint);
            canvas.drawText(graphicsCardText, currentPageWidth + 195, currentPageHeight + 205, paint);
            canvas.drawText(graphics_cardDescription, currentPageWidth + 335, currentPageHeight + 205, paint);
            canvas.drawText(String.valueOf(graphicsCardPrice), currentPageWidth + 525, currentPageHeight + 205, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);

        }
        if (ssd.isChecked()) {
            ssdText = ssd_name.getText().toString();
            ssdPrice = Integer.parseInt(ssd_price.getText().toString());
            if (ssd_description.getText().toString().equals("")) {
                ssd_description.setText("");
            } else {
                ssdDescription = ssd_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialNo + 1), currentPageWidth + 55, currentPageHeight + 225, paint);
            canvas.drawText("SSD", currentPageWidth + 95, currentPageHeight + 225, paint);
            canvas.drawText(ssdText, currentPageWidth + 195, currentPageHeight + 225, paint);
            canvas.drawText(ssdDescription, currentPageWidth + 335, currentPageHeight + 225, paint);
            canvas.drawText(String.valueOf(ssdPrice), currentPageWidth + 525, currentPageHeight + 225, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);

        }
        if (hdd.isChecked()) {
            hddText = hdd_name.getText().toString();
            hddPrice = Integer.parseInt(hdd_price.getText().toString());
            if (hdd_description.getText().toString().equals("")) {
                hdd_description.setText("");
            } else {
                hddDescription = hdd_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialNo + 1), currentPageWidth + 55, currentPageHeight + 245, paint);
            canvas.drawText("HDD", currentPageWidth + 95, currentPageHeight + 245, paint);
            canvas.drawText(hddText, currentPageWidth + 195, currentPageHeight + 245, paint);
            canvas.drawText(hddDescription, currentPageWidth + 335, currentPageHeight + 245, paint);
            canvas.drawText(String.valueOf(hddPrice), currentPageWidth + 525, currentPageHeight + 245, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);

        }
        if (power_supply.isChecked()) {
            powerSupplyText = power_supply_name.getText().toString();
            powerSupplyPrice = Integer.parseInt(power_supply_price.getText().toString());
            if (power_supply_description.getText().toString().equals("")) {
                power_supply_description.setText("");
            } else {
                power_supplyDescription = power_supply_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialNo + 1), currentPageWidth + 55, currentPageHeight + 265, paint);
            canvas.drawText("Power Supply", currentPageWidth + 95, currentPageHeight + 265, paint);
            canvas.drawText(powerSupplyText, currentPageWidth + 195, currentPageHeight + 265, paint);
            canvas.drawText(power_supplyDescription, currentPageWidth + 335, currentPageHeight + 265, paint);
            canvas.drawText(String.valueOf(powerSupplyPrice), currentPageWidth + 525, currentPageHeight + 265, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);

        }
        if (headset.isChecked()) {
            headsetText = headset_name.getText().toString();
            headsetPrice = Integer.parseInt(headset_price.getText().toString());
            if (headset_description.getText().toString().equals("")) {
                headset_description.setText("");
            } else {
                headsetDescription = headset_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialNo + 1), currentPageWidth + 55, currentPageHeight + 285, paint);
            canvas.drawText("Headset", currentPageWidth + 95, currentPageHeight + 285, paint);
            canvas.drawText(headsetText, currentPageWidth + 195, currentPageHeight + 285, paint);
            canvas.drawText(headsetDescription, currentPageWidth + 335, currentPageHeight + 285, paint);
            canvas.drawText(String.valueOf(headsetPrice), currentPageWidth + 525, currentPageHeight + 285, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);

        }
        if (keyboard.isChecked()) {
            keyboardText = keyboard_name.getText().toString();
            keyboardPrice = Integer.parseInt(keyboard_price.getText().toString());
            if (keyboard_description.getText().toString().equals("")) {
                keyboard_description.setText("");
            } else {
                keyboardDescription = keyboard_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialNo + 1), currentPageWidth + 55, currentPageHeight + 305, paint);
            canvas.drawText("Keyboard", currentPageWidth + 95, currentPageHeight + 305, paint);
            canvas.drawText(keyboardText, currentPageWidth + 195, currentPageHeight + 305, paint);
            canvas.drawText(keyboardDescription, currentPageWidth + 335, currentPageHeight + 305, paint);
            canvas.drawText(String.valueOf(keyboardPrice), currentPageWidth + 525, currentPageHeight + 305, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);

        }
        if (mouse.isChecked()) {
            mouseText = mouse_name.getText().toString();
            mousePrice = Integer.parseInt(mouse_price.getText().toString());
            if (mouse_description.getText().toString().equals("")) {
                mouse_description.setText("");
            } else {
                mouseDescription = mouse_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialNo + 1), currentPageWidth + 55, currentPageHeight + 325, paint);
            canvas.drawText("Mouse", currentPageWidth + 95, currentPageHeight + 325, paint);
            canvas.drawText(mouseText, currentPageWidth + 195, currentPageHeight + 325, paint);
            canvas.drawText(mouseDescription, currentPageWidth + 335, currentPageHeight + 325, paint);
            canvas.drawText(String.valueOf(mousePrice), currentPageWidth + 525, currentPageHeight + 325, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);

        }
        if (cabinet.isChecked()) {
            cabinetText = cabinet_name.getText().toString();
            cabinetPrice = Integer.parseInt(cabinet_price.getText().toString());
            if (cabinet_description.getText().toString().equals("")) {
                cabinet_description.setText("");
            } else {
                cabinetDescription = cabinet_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialNo + 1), currentPageWidth + 55, currentPageHeight + 345, paint);
            canvas.drawText("Cabinet", currentPageWidth + 95, currentPageHeight + 345, paint);
            canvas.drawText(cabinetText, currentPageWidth + 195, currentPageHeight + 345, paint);
            canvas.drawText(cabinetDescription, currentPageWidth + 335, currentPageHeight + 345, paint);
            canvas.drawText(String.valueOf(cabinetPrice), currentPageWidth + 525, currentPageHeight + 345, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);

        }
        if (monitor.isChecked()) {
            monitorText = monitor_name.getText().toString();
            monitorPrice = Integer.parseInt(monitor_price.getText().toString());
            if (monitor_description.getText().toString().equals("")) {
                monitor_description.setText("");
            } else {
                monitorDescription = monitor_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialNo + 1), currentPageWidth + 55, currentPageHeight + 365, paint);
            canvas.drawText("Monitor", currentPageWidth + 95, currentPageHeight + 365, paint);
            canvas.drawText(monitorText, currentPageWidth + 195, currentPageHeight + 365, paint);
            canvas.drawText(monitorDescription, currentPageWidth + 335, currentPageHeight + 365, paint);
            canvas.drawText(String.valueOf(monitorPrice), currentPageWidth + 525, currentPageHeight + 365, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);

        }
        if (case_fans.isChecked()) {
            caseFansText = case_fans_name.getText().toString();
            caseFansPrice = Integer.parseInt(case_fans_price.getText().toString());
            if (case_fans_description.getText().toString().equals("")) {
                case_fans_description.setText("");
            } else {
                case_fansDescription = case_fans_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialNo + 1), currentPageWidth + 55, currentPageHeight + 385, paint);
            canvas.drawText("Case Fans", currentPageWidth + 95, currentPageHeight + 385, paint);
            canvas.drawText(caseFansText, currentPageWidth + 195, currentPageHeight + 385, paint);
            canvas.drawText(case_fansDescription, currentPageWidth + 335, currentPageHeight + 385, paint);
            canvas.drawText(String.valueOf(caseFansPrice), currentPageWidth + 525, currentPageHeight + 385, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);

        }
        if (cable.isChecked()) {
            cableText = cable_name.getText().toString();
            cablePrice = Integer.parseInt(cable_price.getText().toString());
            if (cable_description.getText().toString().equals("")) {
                cable_description.setText("");
            } else {
                cableDescription = cable_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialNo + 1), currentPageWidth + 55, currentPageHeight + 405, paint);
            canvas.drawText("Cable", currentPageWidth + 95, currentPageHeight + 405, paint);
            canvas.drawText(cableText, currentPageWidth + 195, currentPageHeight + 405, paint);
            canvas.drawText(cableDescription, currentPageWidth + 335, currentPageHeight + 405, paint);
            canvas.drawText(String.valueOf(cablePrice), currentPageWidth + 525, currentPageHeight + 405, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);

        }
        if (service.isChecked()) {
            serviceText = service_name.getText().toString();
            servicePrice = Integer.parseInt(service_price.getText().toString());
            if (service_description.getText().toString().equals("")) {
                service_description.setText("");
            } else {
                serviceDescription = service_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialNo + 1), currentPageWidth + 55, currentPageHeight + 425, paint);
            canvas.drawText("Service", currentPageWidth + 95, currentPageHeight + 425, paint);
            canvas.drawText(serviceText, currentPageWidth + 195, currentPageHeight + 425, paint);
            canvas.drawText(serviceDescription, currentPageWidth + 335, currentPageHeight + 425, paint);
            canvas.drawText(String.valueOf(servicePrice), currentPageWidth + 525, currentPageHeight + 425, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);

        }
        if (cooler.isChecked()) {
            coolerText = cooler_name.getText().toString();
            coolerPrice = Integer.parseInt(cooler_price.getText().toString());
            if (cooler_description.getText().toString().equals("")) {
                cooler_description.setText("");
            } else {
                coolerDescription = cooler_description.getText().toString();
            }
            canvas.drawText(String.valueOf(serialNo + 1), currentPageWidth + 55, currentPageHeight + 445, paint);
            canvas.drawText("Cooler", currentPageWidth + 95, currentPageHeight + 445, paint);
            canvas.drawText(coolerText, currentPageWidth + 195, currentPageHeight + 445, paint);
            canvas.drawText(coolerDescription, currentPageWidth + 335, currentPageHeight + 445, paint);
            canvas.drawText(String.valueOf(coolerPrice), currentPageWidth + 525, currentPageHeight + 445, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);

        }
        /*
        canvas.drawText(cursor.getString(4), 35, 280, paint);
        canvas.drawText(String.valueOf(cursor.getInt(5)), 100, 280, paint);
        canvas.drawText(String.valueOf(cursor.getInt(6)), 190, 280, paint);

        paint.setColor(Color.rgb(150, 150, 150));
        canvas.drawRect(30, 290, canvas.getWidth() - 40, 295, paint);

        paint.setColor(Color.BLACK);
        canvas.drawText("Sub Total", 100, 310, paint);
        canvas.drawText("Tax 4%", 100, 320, paint);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("TOTAL", 100, 335, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));


        canvas.drawText(String.valueOf(cursor.getInt(6)), 265, 350, paint);
        canvas.drawText(String.valueOf(cursor.getInt(6) * 4 / 100), 265, 370, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText(String.valueOf(cursor.getInt(6) + (cursor.getInt(6) * 4 / 100)), 265, 390, paint);

        canvas.drawText("Make all check payable to \"Custom Builds\"", 50, 500, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        canvas.drawText("Thank you very much", 50, 520, paint);
        pdfDocument.finishPage(page);
         */

        canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight + 176, paint);

        canvas.drawText("", currentPageWidth + 95, currentPageHeight + 490, paint);
        canvas.drawText("TOTAL AMOUNT", currentPageWidth + 195, currentPageHeight + 490, paint);
        canvas.drawText("", currentPageWidth + 335, currentPageHeight + 490, paint);
        canvas.drawText(String.valueOf(totalAmount), currentPageWidth + 525, currentPageHeight + 490, paint);


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


        //File file = new File(this.getExternalFilesDir("/"), cursor.getInt(0) + "_CustomBuilds.pdf");
        File file = new File(this.getExternalFilesDir("/"), quoteNo + "_CustomBuilds.pdf");
        quoteNo++;

        try {
            pdfDocument.finishPage(page);
            pdfDocument.writeTo(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfDocument.close();
    }

    private int updateSerialNo(int serialNo) {
        return serialReturn = serialNo + 1;
    }

    public void printOld(View view) {
    }
}