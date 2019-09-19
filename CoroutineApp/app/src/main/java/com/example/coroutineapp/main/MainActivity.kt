package com.example.coroutineapp.main

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
//import com.example.android.kotlincoroutines.R
import com.example.coroutineapp.R

/**
 * Show layout.activity_main and setup data binding.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Inflate layout.activity_main and setup data binding.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val rootLayout: ConstraintLayout = findViewById(R.id.rootLayout)
        val title: TextView = findViewById(R.id.title)
        val spinner: ProgressBar = findViewById(R.id.spinner)

        // Get MainViewModel by passing a database to the factory
        val database = getDatabase(this)
        val repository = TitleRepository(MainNetworkImpl, database.titleDao)
        val viewModel = ViewModelProviders
            .of(this, MainViewModel.FACTORY(repository))
            .get(MainViewModel::class.java)

        // When rootLayout is clicked call onMainViewClicked in ViewModel
        rootLayout.setOnClickListener {
            viewModel.onMainViewClicked()
        }

        // update the title when the [MainViewModel.title] changes
        viewModel.title.observe(this, Observer { value ->
            value?.let {
                title.text = it
            }
        })

        // show the spinner when [MainViewModel.spinner] is true
        viewModel.spinner.observe(this, Observer { value ->
            value?.let { show ->
                spinner.visibility = if (show) View.VISIBLE else View.GONE
            }
        })

        // Show a snackbar whenever the [ViewModel.snackbar] is updated a non-null value
        viewModel.snackbar.observe(this, Observer { text ->
            text?.let {
                Snackbar.make(rootLayout, text, Snackbar.LENGTH_SHORT).show()
                viewModel.onSnackbarShown()
            }
        })
    }
}

