package in.hr.android.inventorymain;

import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity_bill_to_PDF extends AppCompatActivity {

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

    float totalAmount, taxAmount, priceAmount, rateAmount;
    int serialReturn;

    String customerName, customerBillName, customerMobileNumber, customerAddress1, customerAddress2,
            customerAddress3, customerGst, invoiceNumber;

    EditText customer_name, customerBill_name, customerMobile_number, customer_address1,
            customer_address2, customer_address3, customer_gst, invoice_number;

    EditText processor_name, motherboard_name, ram_name, graphics_card_name, power_supply_name,
            ssd_name, hdd_name, cooler_name, cabinet_name, case_fans_name, keyboard_name,
            mouse_name, monitor_name, service_name, extras_name, headset_name;

    String processorText, motherboardText, ramText, graphicsCardText,
            ssdText, hddText, powerSupplyText, headsetText, keyboardText,
            mouseText, cabinetText, monitorText, caseFansText, extrasText, serviceText, coolerText;

    EditText processor_price, motherboard_price, ram_price, graphics_card_price, power_supply_price,
            ssd_price, hdd_price, cooler_price, cabinet_price, case_fans_price, keyboard_price,
            mouse_price, monitor_price, service_price, extras_price, headset_price;

    EditText processor_rate, motherboard_rate, ram_rate, graphics_card_rate, power_supply_rate,
            ssd_rate, hdd_rate, cooler_rate, cabinet_rate, case_fans_rate, keyboard_rate,
            mouse_rate, monitor_rate, service_rate, extras_rate, headset_rate;

    float processorRate, motherboardRate, ramRate, graphicsCardRate,
            ssdRate, hddRate, powerSupplyRate, headsetRate, keyboardRate,
            mouseRate, cabinetRate, monitorRate, caseFansRate, extrasRate,
            serviceRate, coolerRate;

    EditText processor_tax, motherboard_tax, ram_tax, graphics_card_tax, power_supply_tax,
            ssd_tax, hdd_tax, cooler_tax, cabinet_tax, case_fans_tax, keyboard_tax,
            mouse_tax, monitor_tax, service_tax, extras_tax, headset_tax;

    float processorTax, motherboardTax, ramTax, graphics_cardTax, power_supplyTax,
            ssdTax, hddTax, coolerTax, cabinetTax, case_fansTax, keyboardTax,
            mouseTax, monitorTax, serviceTax, extrasTax, headsetTax;

    CheckBox processor, motherboard, ram, graphics_card, power_supply, ssd, hdd, cooler, cabinet,
            case_fans, keyboard, mouse, monitor, service, extras, headset;

    EditText processor_description, motherboard_description, ram_description, graphics_card_description,
            power_supply_description, ssd_description, hdd_description, cooler_description, cabinet_description,
            case_fans_description, keyboard_description,
            mouse_description, monitor_description, service_description, extras_description, headset_description;

    EditText processor_description2, motherboard_description2, ram_description2, graphics_card_description2,
            power_supply_description2, ssd_description2, hdd_description2, cooler_description2, cabinet_description2,
            case_fans_description2, keyboard_description2,
            mouse_description2, monitor_description2, service_description2, extras_description2, headset_description2;

    String processorDescription, motherboardDescription, ramDescription, graphics_cardDescription,
            power_supplyDescription, ssdDescription, hddDescription, coolerDescription, cabinetDescription,
            case_fansDescription, keyboardDescription,
            mouseDescription, monitorDescription, serviceDescription, extrasDescription, headsetDescription;

    String processorDescription2, motherboardDescription2, ramDescription2, graphics_cardDescription2,
            power_supplyDescription2, ssdDescription2, hddDescription2, coolerDescription2, cabinetDescription2,
            case_fansDescription2, keyboardDescription2,
            mouseDescription2, monitorDescription2, serviceDescription2, extrasDescription2, headsetDescription2;

    float processorPrice = 0, motherboardPrice = 0, ramPrice = 0, graphicsCardPrice = 0,
            ssdPrice = 0, hddPrice = 0, powerSupplyPrice = 0, headsetPrice = 0, keyboardPrice = 0,
            mousePrice = 0, cabinetPrice = 0, monitorPrice = 0, caseFansPrice = 0, extrasPrice = 0,
            servicePrice = 0, coolerPrice = 0;

    Date date = new Date();

    SQLiteDatabase sqLiteDatabase;
    Bill_to_pdf_helper billHelper;

    String datePattern = "dd-MM-YYYY";
    SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

    String timePattern = "hh:mm a";
    SimpleDateFormat timeFormat = new SimpleDateFormat(timePattern);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_to_pdf);
        callFindViewByID();
        billHelper = new Bill_to_pdf_helper(this);
        sqLiteDatabase = billHelper.getWritableDatabase();
    }
/*
    public void check(CheckBox checkBox, EditText NameText, EditText Description, EditText Price,
                 EditText Rate, EditText Tax, String text, String description ,int price,Float rate, Float tax) {

        if (checkBox.isChecked()) {
           /* if (processor_name.getText().toString().equals(null)) {
                processor_name.setText("");}
                   if (processor_description.getText().toString().equals(null)) {
                processor_description.setText("");
            }
            /*
            text = NameText.getText().toString();
            description = Description.getText().toString();
            if (Price.getText().toString().isEmpty()) {
                toast((float) price);
                // motherboard_price.setBackgroundColor(Color.RED);
                Price.setHighlightColor(Color.RED);
            } else {
                price = Integer.parseInt(Price.getText().toString());
                rate = Float.parseFloat(Rate.getText().toString());
                tax = Float.parseFloat(Tax.getText().toString());
            }
        }
    }
   /* public void draw(Canvas canvas ) {
        canvas.drawText(String.valueOf(serialNo), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);
        canvas.drawText("Processor", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
        canvas.drawText(processorText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
        canvas.drawText(processorDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
        canvas.drawText("RS.  " + String.valueOf(processorPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
        canvas.drawText(String.valueOf(processorRate), currentPageWidth + currentPositionWidth + differentialWidthRate, currentPageHeight + currentPositionHeight, paint);
        canvas.drawText(String.valueOf(processorTax), currentPageWidth + currentPositionWidth + differentialWidthTax, currentPageHeight + currentPositionHeight, paint);
        updateSerialNo(serialNo);
    }
    */

    private void callFindViewByID() {

        customerBill_name = findViewById(R.id.editTextBillName);
        customerMobile_number = findViewById(R.id.editTextCustomerNumber);
        customer_address1 = findViewById(R.id.editTextCustomerAddress1);
        customer_address2 = findViewById(R.id.editTextCustomerAddress2);
        customer_address3 = findViewById(R.id.editTextCustomerAddress3);
        customer_gst = findViewById(R.id.editTextCustomerGST);
        customer_name = findViewById(R.id.editTextCustomerName);
        invoice_number = findViewById(R.id.editTextInvoiceNumber);

        processor = findViewById(R.id.checkBoxProcessor);
        processor_name = findViewById(R.id.editTextProcessorName);
        processor_price = findViewById(R.id.editTextProcessorPrice);
        processor_description = findViewById(R.id.processorDescription);
        processor_description2 = findViewById(R.id.processorDescription2);
        processor_tax = findViewById(R.id.editTextProcessorPriceTax);
        processor_rate = findViewById(R.id.editTextProcessorRate);

        motherboard = findViewById(R.id.checkBoxMotherBoard);
        motherboard_name = findViewById(R.id.editTextMotherboardName);
        motherboard_price = findViewById(R.id.editTextMotherboardPrice);
        motherboard_description = findViewById(R.id.motherboardDescription);
        motherboard_description2 = findViewById(R.id.motherboardDescription2);
        motherboard_tax = findViewById(R.id.editTextMotherboardPriceTax);
        motherboard_rate = findViewById(R.id.editTextMotherboardRate);

        ram = findViewById(R.id.checkBoxRam);
        ram_name = findViewById(R.id.editTextRamName);
        ram_price = findViewById(R.id.editTextRamPrice);
        ram_description = findViewById(R.id.ramDescription);
        ram_description2 = findViewById(R.id.ramDescription2);
        ram_tax = findViewById(R.id.editTextRamPriceTax);
        ram_rate = findViewById(R.id.editTextRamRate);

        graphics_card = findViewById(R.id.checkBoxGraphicsCard);
        graphics_card_name = findViewById(R.id.editTextGraphicsCardName);
        graphics_card_price = findViewById(R.id.editTextGraphicsCardPrice);
        graphics_card_description = findViewById(R.id.graphicsCardDescription);
        graphics_card_description2 = findViewById(R.id.graphicsCardDescription2);
        graphics_card_tax = findViewById(R.id.editTextGraphicsCardPriceTax);
        graphics_card_rate = findViewById(R.id.editTextGraphicsCardRate);

        ssd = findViewById(R.id.checkBoxSSD);
        ssd_name = findViewById(R.id.editTextSSDName);
        ssd_price = findViewById(R.id.editTextSSDPrice);
        ssd_description = findViewById(R.id.SSDDescription);
        ssd_description2 = findViewById(R.id.SSDDescription2);
        ssd_tax = findViewById(R.id.editTextSSDPriceTax);
        ssd_rate = findViewById(R.id.editTextSSDRate);

        hdd = findViewById(R.id.checkBoxHDD);
        hdd_name = findViewById(R.id.editTextHDDName);
        hdd_price = findViewById(R.id.editTextHDDPrice);
        hdd_description = findViewById(R.id.HDDDescription);
        hdd_description2 = findViewById(R.id.HDDDescription2);
        hdd_tax = findViewById(R.id.editTextHDDPriceTax);
        hdd_rate = findViewById(R.id.editTextHDDRate);

        power_supply = findViewById(R.id.checkBoxPowerSupply);
        power_supply_name = findViewById(R.id.editTextPowerSupplyName);
        power_supply_price = findViewById(R.id.editTextPowerSupplyPrice);
        power_supply_description = findViewById(R.id.powerSupplyDescription);
        power_supply_tax = findViewById(R.id.editTextPowerSupplyPriceTax);
        power_supply_rate = findViewById(R.id.editTextPowerSupplyRate);

        cooler = findViewById(R.id.checkBoxCooler);
        cooler_name = findViewById(R.id.editTextCoolerName);
        cooler_price = findViewById(R.id.editTextCoolerPrice);
        cooler_description = findViewById(R.id.coolerDescription);
        cooler_tax = findViewById(R.id.editTextCoolerPriceTax);
        cooler_rate = findViewById(R.id.editTextCoolerRate);

        cabinet = findViewById(R.id.checkBoxCabinet);
        cabinet_name = findViewById(R.id.editTextCabinetName);
        cabinet_price = findViewById(R.id.editTextCabinetPrice);
        cabinet_description = findViewById(R.id.cabinetDescription);
        cabinet_tax = findViewById(R.id.editTextCabinetPriceTax);
        cabinet_rate = findViewById(R.id.editTextCabinetRate);

        case_fans = findViewById(R.id.checkBoxCaseFans);
        case_fans_name = findViewById(R.id.editTextCaseFansName);
        case_fans_price = findViewById(R.id.editTextCaseFansPrice);
        case_fans_description = findViewById(R.id.caseFansDescription);
        case_fans_tax = findViewById(R.id.editTextCaseFansPriceTax);
        case_fans_rate = findViewById(R.id.editTextCaseFansRate);

        keyboard = findViewById(R.id.checkBoxKeyboard);
        keyboard_name = findViewById(R.id.editTextKeyboardName);
        keyboard_price = findViewById(R.id.editTextKeyboardPrice);
        keyboard_description = findViewById(R.id.keyboardDescription);
        keyboard_tax = findViewById(R.id.editTextKeyboardPriceTax);
        keyboard_rate = findViewById(R.id.editTextKeyboardRate);

        mouse = findViewById(R.id.checkBoxMouse);
        mouse_name = findViewById(R.id.editTextMouseName);
        mouse_price = findViewById(R.id.editTextMousePrice);
        mouse_description = findViewById(R.id.mouseDescription);
        mouse_tax = findViewById(R.id.editTextMousePriceTax);
        mouse_rate = findViewById(R.id.editTextMouseRate);

        monitor = findViewById(R.id.checkBoxMonitor);
        monitor_name = findViewById(R.id.editTextMonitorName);
        monitor_price = findViewById(R.id.editTextMonitorPrice);
        monitor_description = findViewById(R.id.monitorDescription);
        monitor_tax = findViewById(R.id.editTextMonitorPriceTax);
        monitor_rate = findViewById(R.id.editTextMonitorRate);

        extras = findViewById(R.id.checkBoxExtras);
        extras_name = findViewById(R.id.editTextExtrasName);
        extras_price = findViewById(R.id.editTextExtrasPrice);
        extras_description = findViewById(R.id.extrasDescription);
        extras_tax = findViewById(R.id.editTextExtrasPriceTax);
        extras_rate = findViewById(R.id.editTextExtrasRate);

        headset = findViewById(R.id.checkBoxHeadSet);
        headset_name = findViewById(R.id.editTextHeadsetName);
        headset_price = findViewById(R.id.editTextHeadsetPrice);
        headset_description = findViewById(R.id.headsetDescription);
        headset_tax = findViewById(R.id.editTextHeadsetPriceTax);
        headset_rate = findViewById(R.id.editTextHeadsetRate);

        service = findViewById(R.id.checkBoxService);
        service_name = findViewById(R.id.editTextServiceName);
        service_price = findViewById(R.id.editTextServicePrice);
        service_description = findViewById(R.id.serviceDescription);
        service_tax = findViewById(R.id.editTextServicePriceTax);
        service_rate = findViewById(R.id.editTextServiceRate);
    }

    public void toast(String thing) {
        Toast.makeText(this, "Please Enter: " + thing, Toast.LENGTH_SHORT).show();
    }

    public void verifyButton(View view) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Verifying Details");
        progressDialog.show();
        try {
            calculateAll();
        } catch (Exception e) {
            toast(String.valueOf(e));
        }
        progressDialog.dismiss();
    }

    public void saveAndPrint(View view) {
        int serialNo = 1;

        // global page width and height
        int totalPageWidth = 595;
        int totalPageHeight = 841;

        int currentPageWidth = 0;
        int currentPageHeight = 0;

        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();

        customerBillName = customerBill_name.getText().toString();
        customerMobileNumber = customerMobile_number.getText().toString();
        customerAddress1 = customer_address1.getText().toString();
        customerAddress2 = customer_address2.getText().toString();
        customerAddress3 = customer_address3.getText().toString();
        customerGst = customer_gst.getText().toString();
        customerName = customer_name.getText().toString();
        invoiceNumber = invoice_number.getText().toString();

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

        paint.setTextSize(10);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("TAX INVOICE", currentPageWidth + 250, currentPageHeight + 30, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        canvas.drawText("Bill Number :", totalPageWidth - 200, currentPageHeight + 40, paint);
        canvas.drawText(String.valueOf(invoiceNumber), totalPageWidth - 120, currentPageHeight + 40, paint);

        canvas.drawText("Date :", currentPageWidth + 25, currentPageHeight + 40, paint);
        canvas.drawText(dateFormat.format(date.getTime()), 50, 40, paint);

        canvas.drawText("Time :", currentPageWidth + 110, currentPageHeight + 40, paint);
        //canvas.drawText(timeFormat.format(cursor.getLong(3)), canvas.getWidth() - 40, 200, paint);
        canvas.drawText(timeFormat.format(date.getTime()), 150, 40, paint);

        canvas.drawRect(currentPageWidth + 10, currentPageHeight + 45, totalPageWidth - 10, currentPageHeight + 47, paint);

        paint.setTextSize(15);
        canvas.drawText("CUSTOM BUILDS", currentPageWidth + 250, currentPageHeight + 65, paint);

        paint.setTextSize(8);
        canvas.drawText("#21, 1st Main, Rajeev Gandhi Nagar, Nandini Layout, Bengaluru 96", currentPageWidth + 200, currentPageHeight + 75, paint);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // billing from
        paint.setTextSize(9);
        paint.setColor(Color.rgb(150, 150, 150));
        canvas.drawRect(currentPageWidth + 15, currentPageHeight + 85, currentPageWidth + 50, currentPageHeight + 95, paint);

        paint.setColor(Color.WHITE);
        canvas.drawText("FROM", currentPageWidth + 20, currentPageHeight + 94, paint);

        paint.setColor(Color.BLACK);
        canvas.drawText("KIRAN R ", currentPageWidth + 20, currentPageHeight + 110, paint);
        canvas.drawText("CUSTOM BUILDS TECHNOLOGY", currentPageWidth + 20, currentPageHeight + 120, paint);
        canvas.drawText("NANDINI LAYOUT, BENGALURU 96", currentPageWidth + 20, currentPageHeight + 130, paint);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("MOBILE NUMBER: 9739942971", currentPageWidth + 20, currentPageHeight + 140, paint);

        canvas.drawText("GST IN: 29JVVPK7688R1ZL", totalPageWidth - 150, currentPageHeight + 130, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        //BILLING TO
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setTextSize(9);
        paint.setColor(Color.rgb(150, 150, 150));
        canvas.drawRect(currentPageWidth + 15, currentPageHeight + 155, currentPageWidth + 50, currentPageHeight + 165, paint);

        paint.setColor(Color.WHITE);
        canvas.drawText("TO", currentPageWidth + 20, currentPageHeight + 164, paint);

        paint.setColor(Color.BLACK);
        canvas.drawText(customerName, currentPageWidth + 20, currentPageHeight + 180, paint);
        canvas.drawText(customerMobileNumber, currentPageWidth + 20, currentPageHeight + 190, paint);
        canvas.drawText(customerAddress1, currentPageWidth + 20, currentPageHeight + 200, paint);
        canvas.drawText(customerAddress2, currentPageWidth + 20, currentPageHeight + 210, paint);
        canvas.drawText(customerAddress3, currentPageWidth + 20, currentPageHeight + 220, paint);

        canvas.drawText("GST: " + customerGst, totalPageWidth - 150, currentPageHeight + 190, paint);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // draw divider line
        canvas.drawRect(currentPageWidth + 10, currentPageHeight + 235, totalPageWidth - 10, currentPageHeight + 237, paint);

        paint.setTextSize(8);

        // create rectangular bar
        paint.setColor(Color.rgb(150, 150, 150));
        canvas.drawRect(currentPageWidth + 15, currentPageHeight + 240, totalPageWidth - 15, currentPageHeight + 255, paint);

        //table heading
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setColor(Color.WHITE);
        canvas.drawText("Sl no", currentPageWidth + 18, currentPageHeight + 250, paint);
        canvas.drawText("Part", currentPageWidth + 50, currentPageHeight + 250, paint);
        canvas.drawText("Brand", currentPageWidth + 120, currentPageHeight + 250, paint);
        canvas.drawText("Description", currentPageWidth + 200, currentPageHeight + 250, paint);
        canvas.drawText("Rate", totalPageWidth - 155, currentPageHeight + 250, paint);
        canvas.drawText("TAX", totalPageWidth - 110, currentPageHeight + 250, paint);
        canvas.drawText("Amount", totalPageWidth - 60, currentPageHeight + 250, paint);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setColor(Color.BLACK);

        int currentPositionWidth = 20;// for serial number width is +35
        int currentPositionHeight = 265;
        int tableRowHeight = 22;

        int differentialWidthTextView = 25;
        int differentialWidthText = 85;
        int differentialWidthDescription = 175;
        int differentialWidthRate = totalPageWidth - 192;
        int differentialWidthTax = totalPageWidth - 130;
        int differentialWidthPrice = totalPageWidth - 90;

        if (processor.isChecked()) {
           /* if (processor_name.getText().toString().equals(null)) {
                processor_name.setText("");}
                   if (processor_description.getText().toString().equals(null)) {
                processor_description.setText("");
            }
            */
            processorText = processor_name.getText().toString();
            processorDescription = processor_description.getText().toString();
            processorDescription2 =processor_description2.getText().toString();

            if (processor_price.getText().toString().isEmpty()) {
                toast(String.valueOf(processorPrice) + "is empty");
                processor_price.setHighlightColor(Color.RED);
            } else {
                processorPrice = Integer.parseInt(processor_price.getText().toString());
                processorRate = Float.parseFloat(processor_rate.getText().toString());
                processorTax = Float.parseFloat(processor_tax.getText().toString());
            }
            canvas.drawText(String.valueOf(serialNo), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText("Processor", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(processorText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(processorDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(processorDescription2, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight +10, paint);
            canvas.drawText("RS.  " + String.valueOf(processorPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(processorRate), currentPageWidth + currentPositionWidth + differentialWidthRate, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(processorTax), currentPageWidth + currentPositionWidth + differentialWidthTax, currentPageHeight + currentPositionHeight, paint);
            updateSerialNo(serialNo);
        }
        if (motherboard.isChecked()) {
            currentPositionHeight = currentPositionHeight + tableRowHeight;
            motherboardText = motherboard_name.getText().toString();
            motherboardDescription = motherboard_description.getText().toString();
            motherboardDescription2 =motherboard_description2.getText().toString();
            if (motherboard_price.getText().toString().isEmpty()) {
                toast(motherboardPrice + "is empty");
            } else {
                motherboardPrice = Integer.parseInt(motherboard_price.getText().toString());
                motherboardRate = Float.parseFloat(motherboard_rate.getText().toString());
                motherboardTax = Float.parseFloat(motherboard_tax.getText().toString());
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//165
            canvas.drawText("Motherboard", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(motherboardText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(motherboardDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(motherboardDescription2, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight+10, paint);
            canvas.drawText("RS. " + String.valueOf(motherboardPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(motherboardRate), currentPageWidth + currentPositionWidth + differentialWidthRate, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(motherboardTax), currentPageWidth + currentPositionWidth + differentialWidthTax, currentPageHeight + currentPositionHeight, paint);
            updateSerialNo(serialReturn);
        }
        if (ram.isChecked()) {
            currentPositionHeight = currentPositionHeight + tableRowHeight;
            ramText = ram_name.getText().toString();
            ramDescription = ram_description.getText().toString();
            ramDescription2 = ram_description2.getText().toString();
            if (ram_price.getText().toString().isEmpty()) {
                toast(ramPrice + "is empty");
            } else {
                ramPrice = Integer.parseInt(ram_price.getText().toString());
                ramRate = Float.parseFloat(ram_rate.getText().toString());
                ramTax = Float.parseFloat(ram_tax.getText().toString());
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint); //185
            canvas.drawText("Ram", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(ramText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(ramDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(ramDescription2, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight+10, paint);
            canvas.drawText("RS. " + String.valueOf(ramPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(ramRate), currentPageWidth + currentPositionWidth + differentialWidthRate, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(ramTax), currentPageWidth + currentPositionWidth + differentialWidthTax, currentPageHeight + currentPositionHeight, paint);
            updateSerialNo(serialReturn);
        }
        if (graphics_card.isChecked()) {
            currentPositionHeight = currentPositionHeight + tableRowHeight;
            graphicsCardText = graphics_card_name.getText().toString();
            graphics_cardDescription = graphics_card_description.getText().toString();
            graphics_cardDescription2 = graphics_card_description2.getText().toString();
            if (graphics_card_price.getText().toString().isEmpty()) {
                toast(graphicsCardPrice + "is empty");
            } else {
                graphicsCardPrice = Integer.parseInt(graphics_card_price.getText().toString());
                graphicsCardRate = Float.parseFloat(graphics_card_rate.getText().toString());
                graphics_cardTax = Float.parseFloat(graphics_card_tax.getText().toString());
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//205
            canvas.drawText("Graphics Card", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(graphicsCardText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(graphics_cardDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(graphics_cardDescription2, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight +10, paint);
            canvas.drawText("RS. " + String.valueOf(graphicsCardPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            canvas.drawText(String.valueOf(graphicsCardRate), currentPageWidth + currentPositionWidth + differentialWidthRate, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(graphics_cardTax), currentPageWidth + currentPositionWidth + differentialWidthTax, currentPageHeight + currentPositionHeight, paint);
            updateSerialNo(serialReturn);
        }
        if (ssd.isChecked()) {
            currentPositionHeight = currentPositionHeight + tableRowHeight;
            ssdText = ssd_name.getText().toString();
            ssdDescription = ssd_description.getText().toString();
            ssdDescription2 = ssd_description2.getText().toString();
            if (ssd_price.getText().toString().isEmpty()) {
                toast(ssdPrice + "is empty");
            } else {
                ssdPrice = Integer.parseInt(ssd_price.getText().toString());
                ssdRate = Float.parseFloat(ssd_rate.getText().toString());
                ssdTax = Float.parseFloat(ssd_tax.getText().toString());
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//225
            canvas.drawText("SSD", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(ssdText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(ssdDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(ssdDescription2, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight+10, paint);
            canvas.drawText("RS. " + String.valueOf(ssdPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            canvas.drawText(String.valueOf(ssdRate), currentPageWidth + currentPositionWidth + differentialWidthRate, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(ssdTax), currentPageWidth + currentPositionWidth + differentialWidthTax, currentPageHeight + currentPositionHeight, paint);
            updateSerialNo(serialReturn);
        }
        if (hdd.isChecked()) {
            currentPositionHeight = currentPositionHeight + tableRowHeight;
            hddText = hdd_name.getText().toString();
            hddDescription = hdd_description.getText().toString();
            hddDescription2 = hdd_description2.getText().toString();
            if (hdd_price.getText().toString().isEmpty()) {
                toast(hddPrice + "is empty");
            } else {
                hddPrice = Integer.parseInt(hdd_price.getText().toString());
                hddRate = Float.parseFloat(hdd_rate.getText().toString());
                hddTax = Float.parseFloat(hdd_tax.getText().toString());
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//245
            canvas.drawText("HDD", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(hddText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(hddDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(hddDescription2, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight+10, paint);
            canvas.drawText("RS. " + String.valueOf(hddPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            canvas.drawText(String.valueOf(hddRate), currentPageWidth + currentPositionWidth + differentialWidthRate, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(hddTax), currentPageWidth + currentPositionWidth + differentialWidthTax, currentPageHeight + currentPositionHeight, paint);
            updateSerialNo(serialReturn);
        }
        if (power_supply.isChecked()) {
            currentPositionHeight = currentPositionHeight + tableRowHeight;
            powerSupplyText = power_supply_name.getText().toString();
            power_supplyDescription = power_supply_description.getText().toString();
            power_supplyDescription2 = power_supply_description2.getText().toString();
            if (power_supply_price.getText().toString().isEmpty()) {
                toast(powerSupplyPrice + "is empty");
            } else {
                powerSupplyPrice = Integer.parseInt(power_supply_price.getText().toString());
                powerSupplyRate = Float.parseFloat(power_supply_rate.getText().toString());
                power_supplyTax = Float.parseFloat(power_supply_tax.getText().toString());
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//265
            canvas.drawText("Power Supply", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(powerSupplyText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(power_supplyDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(power_supplyDescription2, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight+10, paint);
            canvas.drawText("RS. " + String.valueOf(powerSupplyPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            canvas.drawText(String.valueOf(powerSupplyRate), currentPageWidth + currentPositionWidth + differentialWidthRate, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(power_supplyTax), currentPageWidth + currentPositionWidth + differentialWidthTax, currentPageHeight + currentPositionHeight, paint);
            updateSerialNo(serialReturn);
        }
        if (headset.isChecked()) {
            currentPositionHeight = currentPositionHeight + tableRowHeight;
            headsetText = headset_name.getText().toString();
            headsetDescription = headset_description.getText().toString();
            headsetDescription2 = headset_description2.getText().toString();
            if (headset_price.getText().toString().isEmpty()) {
                toast(headsetPrice + "is empty");
            } else {
                headsetPrice = Integer.parseInt(headset_price.getText().toString());
                headsetRate = Float.parseFloat(headset_rate.getText().toString());
                headsetTax = Float.parseFloat(headset_tax.getText().toString());
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//285
            canvas.drawText("Headset", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(headsetText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(headsetDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(headsetDescription2, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight+10, paint);
            canvas.drawText("RS. " + String.valueOf(headsetPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            canvas.drawText(String.valueOf(headsetRate), currentPageWidth + currentPositionWidth + differentialWidthRate, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(headsetTax), currentPageWidth + currentPositionWidth + differentialWidthTax, currentPageHeight + currentPositionHeight, paint);
            updateSerialNo(serialReturn);
        }
        if (keyboard.isChecked()) {
            currentPositionHeight = currentPositionHeight + tableRowHeight;
            keyboardText = keyboard_name.getText().toString();
            keyboardDescription = keyboard_description.getText().toString();
            keyboardDescription2 = keyboard_description2.getText().toString();
            if (keyboard_price.getText().toString().isEmpty()) {
                toast(keyboardPrice + "is empty");
            } else {
                keyboardPrice = Integer.parseInt(keyboard_price.getText().toString());
                keyboardRate = Float.parseFloat(keyboard_rate.getText().toString());
                keyboardTax = Float.parseFloat(keyboard_tax.getText().toString());
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//305
            canvas.drawText("Keyboard", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(keyboardText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(keyboardDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(keyboardDescription2, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight+10, paint);
            canvas.drawText("RS. " + String.valueOf(keyboardPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            canvas.drawText(String.valueOf(keyboardRate), currentPageWidth + currentPositionWidth + differentialWidthRate, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(keyboardTax), currentPageWidth + currentPositionWidth + differentialWidthTax, currentPageHeight + currentPositionHeight, paint);
            updateSerialNo(serialReturn);
        }
        if (mouse.isChecked()) {
            currentPositionHeight = currentPositionHeight + tableRowHeight;
            mouseText = mouse_name.getText().toString();
            mouseDescription = mouse_description.getText().toString();
            mouseDescription2 = mouse_description2.getText().toString();
            if (mouse_price.getText().toString().isEmpty()) {
                toast(mousePrice + "is empty");
            } else {
                mousePrice = Integer.parseInt(mouse_price.getText().toString());
                mouseRate = Float.parseFloat(mouse_rate.getText().toString());
                mouseTax = Float.parseFloat(mouse_tax.getText().toString());
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//325
            canvas.drawText("Mouse", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(mouseText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(mouseDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(mouseDescription2, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight+10, paint);
            canvas.drawText("RS. " + String.valueOf(mousePrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            canvas.drawText(String.valueOf(mouseRate), currentPageWidth + currentPositionWidth + differentialWidthRate, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(mouseTax), currentPageWidth + currentPositionWidth + differentialWidthTax, currentPageHeight + currentPositionHeight, paint);
            updateSerialNo(serialReturn);
        }
        if (cabinet.isChecked()) {
            currentPositionHeight = currentPositionHeight + tableRowHeight;
            cabinetText = cabinet_name.getText().toString();
            cabinetDescription = cabinet_description.getText().toString();
            cabinetDescription2 = cabinet_description2.getText().toString();
            if (cabinet_price.getText().toString().isEmpty()) {
                toast(cabinetPrice + "is empty");
            } else {
                cabinetPrice = Integer.parseInt(cabinet_price.getText().toString());
                cabinetRate = Float.parseFloat(cabinet_rate.getText().toString());
                cabinetTax = Float.parseFloat(cabinet_tax.getText().toString());
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//345
            canvas.drawText("Cabinet", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(cabinetText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(cabinetDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(cabinetDescription2, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight+10, paint);
            canvas.drawText("RS. " + String.valueOf(cabinetPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            canvas.drawText(String.valueOf(cabinetRate), currentPageWidth + currentPositionWidth + differentialWidthRate, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(cabinetTax), currentPageWidth + currentPositionWidth + differentialWidthTax, currentPageHeight + currentPositionHeight, paint);
            updateSerialNo(serialReturn);
        }
        if (cooler.isChecked()) {
            currentPositionHeight = currentPositionHeight + tableRowHeight;
            coolerText = cooler_name.getText().toString();
            coolerDescription = cooler_description.getText().toString();
            coolerDescription2 = cooler_description2.getText().toString();
            if (cooler_price.getText().toString().isEmpty()) {
                toast(coolerPrice + "is empty");
            } else {
                coolerPrice = Integer.parseInt(cooler_price.getText().toString());
                coolerRate = Float.parseFloat(cooler_rate.getText().toString());
                coolerTax = Float.parseFloat(cooler_tax.getText().toString());
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint); // 445
            canvas.drawText("Cooler", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(coolerText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(coolerDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(coolerDescription2, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight+10, paint);
            canvas.drawText("RS. " + String.valueOf(coolerPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(coolerRate), currentPageWidth + currentPositionWidth + differentialWidthRate, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(coolerTax), currentPageWidth + currentPositionWidth + differentialWidthTax, currentPageHeight + currentPositionHeight, paint);
            updateSerialNo(serialReturn);
        }
        if (monitor.isChecked()) {
            currentPositionHeight = currentPositionHeight + tableRowHeight;
            monitorText = monitor_name.getText().toString();
            monitorDescription = monitor_description.getText().toString();
            monitorDescription2 = monitor_description2.getText().toString();
            if (monitor_price.getText().toString().isEmpty()) {
                toast(monitorPrice + "is empty");
            } else {
                monitorPrice = Integer.parseInt(monitor_price.getText().toString());
                monitorRate = Float.parseFloat(monitor_rate.getText().toString());
                monitorTax = Float.parseFloat(monitor_tax.getText().toString());
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//365
            canvas.drawText("Monitor", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(monitorText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(monitorDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(monitorDescription2, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight+10, paint);
            canvas.drawText("RS. " + String.valueOf(monitorPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            canvas.drawText(String.valueOf(monitorRate), currentPageWidth + currentPositionWidth + differentialWidthRate, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(monitorTax), currentPageWidth + currentPositionWidth + differentialWidthTax, currentPageHeight + currentPositionHeight, paint);
            updateSerialNo(serialReturn);
        }
        if (case_fans.isChecked()) {
            currentPositionHeight = currentPositionHeight + tableRowHeight;
            caseFansText = case_fans_name.getText().toString();
            case_fansDescription = case_fans_description.getText().toString();
            case_fansDescription2 = case_fans_description2.getText().toString();
            if (case_fans_price.getText().toString().isEmpty()) {
                toast(caseFansPrice + "is empty");
            } else {
                caseFansPrice = Integer.parseInt(case_fans_price.getText().toString());
                caseFansRate = Float.parseFloat(case_fans_rate.getText().toString());
                case_fansTax = Float.parseFloat(case_fans_tax.getText().toString());
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//385
            canvas.drawText("Case Fans", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(caseFansText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(case_fansDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(case_fansDescription2, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight+10, paint);
            canvas.drawText("RS. " + String.valueOf(caseFansPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            canvas.drawText(String.valueOf(caseFansRate), currentPageWidth + currentPositionWidth + differentialWidthRate, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(case_fansTax), currentPageWidth + currentPositionWidth + differentialWidthTax, currentPageHeight + currentPositionHeight, paint);
            updateSerialNo(serialReturn);
        }

        if (service.isChecked()) {
            currentPositionHeight = currentPositionHeight + tableRowHeight;
            serviceText = service_name.getText().toString();
            serviceDescription = service_description.getText().toString();
            serviceDescription2 = service_description2.getText().toString();
            if (service_price.getText().toString().isEmpty()) {
                toast(servicePrice + "is empty");
            } else {
                servicePrice = Integer.parseInt(service_price.getText().toString());
                serviceRate = Float.parseFloat(service_rate.getText().toString());
                serviceTax = Float.parseFloat(service_tax.getText().toString());
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//425
            canvas.drawText("Service", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(serviceText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(serviceDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(serviceDescription2, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight+10, paint);
            canvas.drawText("RS. " + String.valueOf(servicePrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            canvas.drawText(String.valueOf(serviceRate), currentPageWidth + currentPositionWidth + differentialWidthRate, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(serviceTax), currentPageWidth + currentPositionWidth + differentialWidthTax, currentPageHeight + currentPositionHeight, paint);
            updateSerialNo(serialReturn);
        }
        if (extras.isChecked()) {
            currentPositionHeight = currentPositionHeight + tableRowHeight;
            extrasText = extras_name.getText().toString();
            extrasDescription = extras_description.getText().toString();
            extrasDescription2 = extras_description2.getText().toString();
            if (extras_price.getText().toString().isEmpty()) {
                toast(extrasPrice + "is empty");
            } else {
                extrasPrice = Integer.parseInt(extras_price.getText().toString());
                extrasRate = Float.parseFloat(extras_rate.getText().toString());
                extrasTax = Float.parseFloat(extras_tax.getText().toString());
            }
            canvas.drawText(String.valueOf(serialReturn), currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);//405
            canvas.drawText("Extras", currentPageWidth + currentPositionWidth + differentialWidthTextView, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(extrasText, currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(extrasDescription, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(extrasDescription2, currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight+10, paint);
            canvas.drawText("RS. " + String.valueOf(extrasPrice), currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);
            //canvas.drawRect(currentPageWidth + 25, currentPageHeight + 175, totalPageWidth - 25, currentPageHeight +176, paint);
            canvas.drawText(String.valueOf(extrasRate), currentPageWidth + currentPositionWidth + differentialWidthRate, currentPageHeight + currentPositionHeight, paint);
            canvas.drawText(String.valueOf(extrasTax), currentPageWidth + currentPositionWidth + differentialWidthTax, currentPageHeight + currentPositionHeight, paint);
            updateSerialNo(serialReturn);
        }

        rateAmount = processorRate + motherboardRate + ramRate + graphicsCardRate + ssdRate + hddRate +
                powerSupplyRate + coolerRate + cabinetRate + extrasRate + serviceRate + caseFansRate +
                keyboardRate + mouseRate + monitorRate + headsetRate;

        priceAmount = processorPrice + motherboardPrice + graphicsCardPrice + ramPrice + ssdPrice
                + hddPrice + powerSupplyPrice + coolerPrice + cabinetPrice + extrasPrice +
                +servicePrice + caseFansPrice + keyboardPrice + mousePrice + monitorPrice + headsetPrice;

        taxAmount = processorTax + motherboardTax + graphics_cardTax + ramTax + ssdTax +
                hddTax + power_supplyTax + coolerTax + cabinetTax + serviceTax + case_fansTax +
                extrasTax + keyboardTax + mouseTax + monitorTax + headsetTax;

        paint.setColor(Color.rgb(150, 150, 150));
        currentPositionHeight = currentPositionHeight + 20;
        canvas.drawRect(currentPageWidth + 15, currentPageHeight + currentPositionHeight - 2, totalPageWidth - 15, currentPageHeight + currentPositionHeight, paint);

        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        currentPositionHeight = currentPositionHeight + 10;
        canvas.drawText("", currentPageWidth + currentPositionWidth, currentPageHeight + currentPositionHeight, paint);
        canvas.drawText("TOTAL", currentPageWidth + currentPositionWidth + differentialWidthText, currentPageHeight + currentPositionHeight, paint);
        canvas.drawText("", currentPageWidth + currentPositionWidth + differentialWidthDescription, currentPageHeight + currentPositionHeight, paint);
        canvas.drawText("RS. " + totalAmount, currentPageWidth + currentPositionWidth + differentialWidthPrice, currentPageHeight + currentPositionHeight, paint);

        canvas.drawText("RS. " + rateAmount, currentPageWidth + currentPositionWidth + differentialWidthRate, currentPageHeight + currentPositionHeight, paint);
        canvas.drawText("RS. " + totalAmount, currentPageWidth + currentPositionWidth + differentialWidthTax, currentPageHeight + currentPositionHeight, paint);

        // draw vertical line TODO: need to change the value of height according to list

        currentPositionHeight = currentPositionHeight + 5; // get current position
        paint.setColor(Color.rgb(150, 150, 150));
        canvas.drawRect(currentPageWidth + 15, currentPageHeight + 250, currentPageWidth + 17, currentPageHeight + currentPositionHeight, paint);
        canvas.drawRect(currentPageWidth + 40, currentPageHeight + 250, currentPageWidth + 42, currentPageHeight + currentPositionHeight, paint);
        canvas.drawRect(currentPageWidth + 102, currentPageHeight + 250, currentPageWidth + 104, currentPageHeight + currentPositionHeight, paint);
        canvas.drawRect(currentPageWidth + 190, currentPageHeight + 250, currentPageWidth + 192, currentPageHeight + currentPositionHeight, paint);
        canvas.drawRect(currentPageWidth + 420, currentPageHeight + 250, currentPageWidth + 422, currentPageHeight + currentPositionHeight, paint);
        canvas.drawRect(currentPageWidth + 475, currentPageHeight + 250, currentPageWidth + 477, currentPageHeight + currentPositionHeight, paint);
        canvas.drawRect(currentPageWidth + 520, currentPageHeight + 250, currentPageWidth + 522, currentPageHeight + currentPositionHeight, paint);
        canvas.drawRect(currentPageWidth + 578, currentPageHeight + 250, currentPageWidth + 580, currentPageHeight + currentPositionHeight, paint);

        // end of table
        canvas.drawRect(currentPageWidth + 15, currentPageHeight + currentPositionHeight, totalPageWidth - 15, currentPageHeight + currentPositionHeight + 2, paint);
/*
        //TODO: INSERT TO DATABASE
        convertQuotationToPdfHelperSql.insertMain(dateFormat.format(date.getTime()), numberText, processorText, processorPrice, processorDescription,
                motherboardText, motherboardPrice, motherboardDescription, ramText, ramPrice, ramDescription, graphicsCardText, graphicsCardPrice, graphics_cardDescription,
                ssdText, ssdPrice,ssdDescription,totalAmount);
*/
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setColor(Color.BLACK);
        paint.setTextSize(8);
        canvas.drawText(warrantyHeading, currentPageWidth + 20, totalPageHeight - 200, paint);
        canvas.drawText(warranty1, currentPageWidth + 40, totalPageHeight - 185, paint);
        canvas.drawText(warranty2, currentPageWidth + 45, totalPageHeight - 175, paint);
        canvas.drawText(warranty3, currentPageWidth + 40, totalPageHeight - 165, paint);
        canvas.drawText(warranty4, currentPageWidth + 40, totalPageHeight - 155, paint);

        canvas.drawText(policyHeading, currentPageWidth + 20, totalPageHeight - 135, paint);
        canvas.drawText(policy1, currentPageWidth + 40, totalPageHeight - 120, paint);
        canvas.drawText(policy2, currentPageWidth + 40, totalPageHeight - 110, paint);
        canvas.drawText(policy3, currentPageWidth + 40, totalPageHeight - 100, paint);
        canvas.drawText(policy4, currentPageWidth + 40, totalPageHeight - 90, paint);
        canvas.drawText(policy5, currentPageWidth + 45, totalPageHeight - 80, paint);
        canvas.drawText(policy6, currentPageWidth + 40, totalPageHeight - 70, paint);

        //CUSTOMER SIGN AND COMPANY SEAL
        canvas.drawText("CUSTOMER SIGN", currentPageWidth + 40, totalPageHeight - 40, paint);
        canvas.drawText("AUTHORIZED SIGN AND SEAL", totalPageWidth - 180, totalPageHeight - 40, paint);


        pdfDocument.finishPage(page);
      /*  }else {
            PdfDocument.PageInfo pageInfo2 = new PdfDocument.PageInfo.Builder(totalPageWidth, totalPageHeight, 2).create();
            PdfDocument.Page page2 = pdfDocument.startPage(pageInfo2);

            pdfDocument.startPage(page2)
            Canvas canvas2 = page2.getCanvas();
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
            paint.setColor(Color.BLACK);
            paint.setTextSize(10);
            canvas2.drawText(warrantyHeading, currentPageWidth + 20, totalPageHeight - 200, paint);
            canvas2.drawText(warranty1, currentPageWidth + 40, totalPageHeight - 185, paint);
            canvas2.drawText(warranty2, currentPageWidth + 45, totalPageHeight - 170, paint);
            canvas2.drawText(warranty3, currentPageWidth + 40, totalPageHeight - 155, paint);
            canvas2.drawText(warranty4, currentPageWidth + 40, totalPageHeight - 140, paint);

            canvas2.drawText(policyHeading, currentPageWidth + 20, totalPageHeight - 120, paint);
            canvas2.drawText(policy1, currentPageWidth + 40, totalPageHeight - 105, paint);
            canvas2.drawText(policy2, currentPageWidth + 40, totalPageHeight - 90, paint);
            canvas2.drawText(policy3, currentPageWidth + 40, totalPageHeight - 75, paint);
            canvas2.drawText(policy4, currentPageWidth + 40, totalPageHeight - 60, paint);
            canvas2.drawText(policy5, currentPageWidth + 45, totalPageHeight - 45, paint);
            canvas2.drawText(policy6, currentPageWidth + 40, totalPageHeight - 30, paint);
            pdfDocument.finishPage(page2);

        }
*/
        billHelper.insertBill(customerBillName, dateFormat.format(date.getTime()), customerMobileNumber, String.valueOf(totalAmount));
        //display total value on screen
        //Toast.makeText(Activity_bill_to_PDF.this, "Total Value is : RS.  " + totalAmount, Toast.LENGTH_SHORT).show();

        //File file = new File(this.getExternalFilesDir("/"), cursor.getInt(0) + "_CustomBuilds.pdf");
        File file = new File(this.getExternalFilesDir("/"), "CustomBuilds.pdf");

        try {
            pdfDocument.writeTo(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfDocument.close();
        Toast.makeText(Activity_bill_to_PDF.this, "Successfully converted to PDF", Toast.LENGTH_SHORT).show();
    }

    private int updateSerialNo(int serialNo) {
        return serialReturn = serialNo + 1;
    }

    public void calculateAll() {
        if (processor.isChecked()) {
            if (processor_price.getText().toString().isEmpty()) {
                toast("processorPrice" + "is empty");
            } else {
                processorPrice = Float.parseFloat(processor_price.getText().toString());
                processorTax = (float) (processorPrice * 0.18);
                processor_tax.setText(String.valueOf(processorTax));
                processorRate = processorPrice - processorTax;
                processor_rate.setText(String.valueOf(processorRate));
            }
        }
        if (motherboard.isChecked()) {
            if (motherboard_price.getText().toString().isEmpty()) {
                toast("motherboardPrice" + "is empty");
            } else {
                motherboardPrice = Float.parseFloat(motherboard_price.getText().toString());
                motherboardTax = (float) (motherboardPrice * 0.18);
                motherboard_tax.setText(String.valueOf(motherboardTax));
                motherboardRate = motherboardPrice - motherboardTax;
                motherboard_rate.setText(String.valueOf(motherboardRate));
            }
        }
        if (ram.isChecked()) {
            if (ram_price.getText().toString().isEmpty()) {
                toast("ramPrice" + "is empty");
            } else {
                ramPrice = Float.parseFloat(ram_price.getText().toString());
                ramTax = (float) (ramPrice * 0.18);
                ram_tax.setText(String.valueOf(ramTax));
                ramRate = ramPrice - ramTax;
                ram_rate.setText(String.valueOf(ramRate));
            }
        }
        if (graphics_card.isChecked()) {
            if (graphics_card_price.getText().toString().isEmpty()) {
                toast("graphicsCardPrice" + "is empty");
            } else {
                graphicsCardPrice = Float.parseFloat(graphics_card_price.getText().toString());
                graphics_cardTax = (float) (graphicsCardPrice * 0.18);
                graphics_card_tax.setText(String.valueOf(graphics_cardTax));
                graphicsCardRate = graphicsCardPrice - graphics_cardTax;
                graphics_card_rate.setText(String.valueOf(graphicsCardRate));
            }
        }
        if (ssd.isChecked()) {
            if (ssd_price.getText().toString().isEmpty()) {
                toast("ssdPrice" + "is empty");
            } else {
                ssdPrice = Float.parseFloat(ssd_price.getText().toString());
                ssdTax = (float) (ssdPrice * 0.18);
                ssd_tax.setText(String.valueOf(ssdTax));
                ssdRate = ssdPrice - ssdTax;
                ssd_rate.setText(String.valueOf(ssdRate));
            }
        }
        if (hdd.isChecked()) {
            if (hdd_price.getText().toString().isEmpty()) {
                toast("hddPrice " + "is empty");
            } else {
                hddPrice = Float.parseFloat(hdd_price.getText().toString());
                hddTax = (float) (hddPrice * 0.18);
                hdd_tax.setText(String.valueOf(hddTax));
                hddRate = hddPrice - hddTax;
                hdd_rate.setText(String.valueOf(hddRate));
            }
        }
        if (power_supply.isChecked()) {
            if (power_supply_price.getText().toString().isEmpty()) {
                toast("powerSupplyPrice" + "is empty");
            } else {
                powerSupplyPrice = Float.parseFloat(power_supply_price.getText().toString());
                power_supplyTax = (float) (powerSupplyPrice * 0.18);
                power_supply_tax.setText(String.valueOf(power_supplyTax));
                powerSupplyRate = powerSupplyPrice - power_supplyTax;
                power_supply_rate.setText(String.valueOf(powerSupplyRate));
            }
        }
        if (headset.isChecked()) {
            if (headset_price.getText().toString().isEmpty()) {
                toast("headsetPrice " + "is empty");
            } else {
                headsetPrice = Float.parseFloat(headset_price.getText().toString());
                headsetTax = (float) (headsetPrice * 0.18);
                headset_tax.setText(String.valueOf(headsetTax));
                headsetRate = headsetPrice - headsetTax;
                headset_rate.setText(String.valueOf(headsetRate));
            }
        }
        if (keyboard.isChecked()) {
            if (keyboard_price.getText().toString().isEmpty()) {
                toast("keyboardPrice " + "is empty");
            } else {
                keyboardPrice = Float.parseFloat(keyboard_price.getText().toString());
                keyboardTax = (float) (keyboardPrice * 0.18);
                keyboard_tax.setText(String.valueOf(keyboardTax));
                keyboardRate = keyboardPrice - keyboardTax;
                keyboard_rate.setText(String.valueOf(keyboardRate));
            }
        }
        if (mouse.isChecked()) {
            if (mouse_price.getText().toString().isEmpty()) {
                toast("mousePrice" + "is empty");
            } else {
                mousePrice = Float.parseFloat(mouse_price.getText().toString());
                mouseTax = (float) (mousePrice * 0.18);
                mouse_tax.setText(String.valueOf(mouseTax));
                mouseRate = mousePrice - mouseTax;
                mouse_rate.setText(String.valueOf(mouseRate));
            }
        }
        if (cabinet.isChecked()) {
            if (cabinet_price.getText().toString().isEmpty()) {
                toast("cabinetPrice" + "is empty");
            } else {
                cabinetPrice = Float.parseFloat(cabinet_price.getText().toString());
                cabinetTax = (float) (cabinetPrice * 0.18);
                cabinet_tax.setText(String.valueOf(cabinetTax));
                cabinetRate = cabinetPrice - cabinetTax;
                cabinet_rate.setText(String.valueOf(cabinetRate));
            }
        }
        if (monitor.isChecked()) {
            if (monitor_price.getText().toString().isEmpty()) {
                toast("monitorPrice" + "is empty");
            } else {
                monitorPrice = Float.parseFloat(monitor_price.getText().toString());
                monitorTax = (float) (monitorPrice * 0.18);
                monitor_tax.setText(String.valueOf(monitorTax));
                monitorRate = monitorPrice - monitorTax;
                monitor_rate.setText(String.valueOf(monitorRate));
            }
        }
        if (case_fans.isChecked()) {
            if (case_fans_price.getText().toString().isEmpty()) {
                toast("caseFansPrice" + "is empty");
            } else {
                caseFansPrice = Float.parseFloat(case_fans_price.getText().toString());
                case_fansTax = (float) (caseFansPrice * 0.18);
                case_fans_tax.setText(String.valueOf(case_fansTax));
                caseFansRate = caseFansPrice - case_fansTax;
                case_fans_rate.setText(String.valueOf(caseFansRate));
            }
        }
        if (extras.isChecked()) {
            if (extras_price.getText().toString().isEmpty()) {
                toast("cablePrice" + "is empty");
            } else {
                extrasPrice = Float.parseFloat(extras_price.getText().toString());
                extrasTax = (float) (extrasPrice * 0.18);
                extras_tax.setText(String.valueOf(extrasTax));
                extrasRate = extrasPrice - extrasTax;
                extras_rate.setText(String.valueOf(extrasRate));
            }
        }
        if (service.isChecked()) {
            if (service_price.getText().toString().isEmpty()) {
                toast("servicePrice" + "is empty");
            } else {
                servicePrice = Float.parseFloat(service_price.getText().toString());
                serviceTax = (float) (servicePrice * 0.18);
                service_tax.setText(String.valueOf(serviceTax));
                serviceRate = servicePrice - serviceTax;
                service_rate.setText(String.valueOf(serviceRate));
            }
        }
        if (cooler.isChecked()) {
            if (cooler_price.getText().toString().isEmpty()) {
                toast("coolerPrice " + "is empty");
            } else {
                coolerPrice = Float.parseFloat(cooler_price.getText().toString());
                coolerTax = (float) (coolerPrice * 0.18);
                cooler_tax.setText(String.valueOf(coolerTax));
                coolerRate = coolerPrice - coolerTax;
                cooler_rate.setText(String.valueOf(coolerRate));
            }
        }

        rateAmount = processorRate + motherboardRate + ramRate + graphicsCardRate + ssdRate + hddRate +
                powerSupplyRate + coolerRate + cabinetRate + extrasRate + serviceRate + caseFansRate + keyboardRate + mouseRate +
                monitorRate + headsetRate;

        priceAmount = processorPrice + motherboardPrice + graphicsCardPrice + ramPrice + ssdPrice
                + hddPrice + powerSupplyPrice + coolerPrice + cabinetPrice + extrasPrice +
                +servicePrice + caseFansPrice + keyboardPrice + mousePrice + monitorPrice + headsetPrice;

        taxAmount = processorTax + motherboardTax + graphics_cardTax + ramTax + ssdTax +
                hddTax + power_supplyTax + coolerTax + cabinetTax + serviceTax + case_fansTax + extrasTax +
                keyboardTax + mouseTax + monitorTax + headsetTax;
    }

}