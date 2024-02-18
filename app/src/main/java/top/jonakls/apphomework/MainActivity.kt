package top.jonakls.apphomework

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val calendar = Calendar.getInstance()

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputDateBirth = this.findViewById<EditText>(R.id.inputDate)

        inputDateBirth.setOnClickListener {
            this.showDatePickerDialog()
        }

        val button = findViewById<Button>(R.id.sendButton)
        button.setOnClickListener {
            val name = findViewById<EditText>(R.id.inputName).text.toString()
            val date = findViewById<EditText>(R.id.inputDate).text.toString()
            val email = findViewById<EditText>(R.id.inputEmail).text.toString()

            if (name.isEmpty() || date.isEmpty() || email.isEmpty()) {
                Toast.makeText(applicationContext, "Los campos están vacios", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Nombre: $name\nFecha nacimiento: $date\nCorreo: $email",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerDialog(
            this,
            dataPickerListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun onDateSelect(day: Int, month: Int, year: Int) {
        calendar.set(Calendar.DAY_OF_MONTH, day)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)

        val format = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
        val dateBirth = format.format(calendar.time)

        val inputDateBirth = this.findViewById<EditText>(R.id.inputDate)
        inputDateBirth.setText(dateBirth)

    }

    private val dataPickerListener =
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            onDateSelect(dayOfMonth, month, year)
        }
}