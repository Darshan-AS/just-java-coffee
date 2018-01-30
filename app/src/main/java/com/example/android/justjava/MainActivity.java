/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * <p>
 * This is the package name our example uses:
 * <p>
 * <p>
 * <p>
 * package com.example.android.justjava;
 */


package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText nameField = findViewById(R.id.name_field);
        Editable name = nameField.getText();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String summary = createOrderSummary(price, hasWhippedCream, hasChocolate, name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto"));
        intent.putExtra(Intent.EXTRA_EMAIL, "darshandon.das465@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java coffee Orders");
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);

    }

    /**
     * Calculates the price of the order.
     *
     * @param addWhippedCream status of WHipped Cream CheckBox
     * @param addChocolate    status of Chocolate Toppings CheckBox
     * @return total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 20;

        if (addWhippedCream)
            basePrice += 5;

        if (addChocolate)
            basePrice += 10;

        return (quantity * basePrice);
    }

    /**
     * Creates the summary of the Order
     *
     * @param price           of the order
     * @param hasWhippedCream status of the Whipped Cream CheckBox
     * @param hasChocolate    status of Chocolate topping CheckBox
     * @param name            Name of the Customer
     * @return order summary
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, Editable name) {
        String summary = getString(R.string.order_summary_name) + name + "\n";

        if (hasWhippedCream)
            summary += getString(R.string.order_summary_whipped_cream) + "\n";

        if (hasChocolate)
            summary += getString(R.string.order_summary_chocolate) + "\n";

        summary += getString(R.string.order_summary_quantity) + quantity + "\n" + getString(R.string.order_summary_price) + price + "\n" + getString(R.string.thank_you);
        return summary;
    }

    /**
     * This method is called when the '+' button is clicked.
     */

    public void increment(View view) {
        if (quantity == 100) {
            Toast maxToast = Toast.makeText(getApplicationContext(), "Maximum no. of Order is 100", Toast.LENGTH_SHORT);
            maxToast.show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the '-' button is clicked.
     */

    public void decrement(View view) {
        if (quantity == 1) {
            Toast minToast = Toast.makeText(getApplicationContext(), "Minimum no. of Order is 1", Toast.LENGTH_SHORT);
            minToast.show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */

    private void displayQuantity(int number) {

        TextView quantityTextView = findViewById(R.id.quantity_text_view);

        quantityTextView.setText("" + number);

    }

}