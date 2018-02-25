package com.sas.rssfeedparser.view.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Window
import com.sas.rssfeedparser.R
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class FeedActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        if (savedInstanceState == null) {
            val fragment = FeedListFragment()

            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment, FeedListFragment.TAG).commit()
        }

        checkforInternetConnection()
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    private fun checkforInternetConnection() {

        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting

        if (!isConnected) {
            showConnectionAlertDialog()
        }
    }

    private fun showConnectionAlertDialog() {
        val builder: AlertDialog.Builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert)
        } else {
            AlertDialog.Builder(this)
        }

        val dialog = builder.setTitle(R.string.connection_dialog_title)
                .setMessage(R.string.connection_dialog_message)
                .setPositiveButton(android.R.string.ok, { dialog, _ ->
                    dialog.dismiss()
                    //finish()
                }).create()
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.show()
    }
}
