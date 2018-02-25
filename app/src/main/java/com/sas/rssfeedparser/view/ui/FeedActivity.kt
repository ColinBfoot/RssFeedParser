package com.sas.rssfeedparser.view.ui

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Window
import com.sas.rssfeedparser.R
import com.sas.rssfeedparser.util.Util
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
        if (!Util.isConnected(this))
            showConnectionAlertDialog()
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
                }).create()
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.show()
    }
}
