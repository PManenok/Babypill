package com.gmail.pmanenok.antibiocalc.presentation.screens.main

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.gmail.pmanenok.antibiocalc.R
import com.gmail.pmanenok.antibiocalc.databinding.ActivityMainBinding
import com.gmail.pmanenok.antibiocalc.presentation.base.BaseMvvmActivity
//import com.google.ads.consent.*
import java.net.MalformedURLException
import java.net.URL


class MainActivity : BaseMvvmActivity<MainViewModel, MainRouter, ActivityMainBinding>() {
    //private var form: ConsentForm? = null
    override fun prodiveViewModel(): MainViewModel {
        return ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun provideRouter(): MainRouter {
        return MainRouter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.mainPrivacy.movementMethod = LinkMovementMethod.getInstance()


        router.goToMainMenu()
        //checkForConsent()
    }

    /*private fun checkForConsent() {
        val consentInformation = ConsentInformation.getInstance(this@MainActivity)
        val publisherIds = arrayOf("pub-id-from-publishers-like-admob")
        consentInformation.requestConsentInfoUpdate(publisherIds, object : ConsentInfoUpdateListener {
            override fun onConsentInfoUpdated(consentStatus: ConsentStatus) {
                // User's consent status successfully updated.

                when (consentStatus) {
                    ConsentStatus.PERSONALIZED -> {
                        Log.d("MainActivity", "Showing Personalized ads")
                        showPersonalizedAds()
                    }
                    ConsentStatus.NON_PERSONALIZED -> {
                        Log.d("MainActivity", "Showing Non-Personalized ads")
                        showNonPersonalizedAds()
                    }
                    ConsentStatus.UNKNOWN -> {
                        Log.d("MainActivity", "Requesting Consent")
                        if (ConsentInformation.getInstance(baseContext).isRequestLocationInEeaOrUnknown) {
                            requestConsent()
                        } else {
                            showPersonalizedAds()
                        }
                    }
                    else -> {
                    }
                }
            }

            override fun onFailedToUpdateConsentInfo(errorDescription: String) {
                // User's consent status failed to update.
            }
        })
    }*/

    /*private fun requestConsent() {
        var privacyUrl: URL? = null
        try {
            // TODO: Replace with your app's privacy policy URL.
            privacyUrl = URL("https://your.privacy.url/")
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            // Handle error.
        }

        form = ConsentForm.Builder(this@MainActivity, privacyUrl)
            .withListener(object : ConsentFormListener() {
                override fun onConsentFormLoaded() {
                    // Consent form loaded successfully.
                    Log.d("MainActivity", "Requesting Consent: onConsentFormLoaded")
                    showForm()
                }

                override fun onConsentFormOpened() {
                    // Consent form was displayed.
                    Log.d("MainActivity", "Requesting Consent: onConsentFormOpened")
                }

                override fun onConsentFormClosed(
                    consentStatus: ConsentStatus, userPrefersAdFree: Boolean?
                ) {
                    Log.d("MainActivity", "Requesting Consent: onConsentFormClosed")
                    if (userPrefersAdFree!!) {
                        // Buy or Subscribe
                        Log.d("MainActivity", "Requesting Consent: User prefers AdFree")
                    } else {
                        Log.d("MainActivity", "Requesting Consent: Requesting consent again")
                        when (consentStatus) {
                            ConsentStatus.PERSONALIZED -> showPersonalizedAds()
                            ConsentStatus.NON_PERSONALIZED -> showNonPersonalizedAds()
                            ConsentStatus.UNKNOWN -> showNonPersonalizedAds()
                        }

                    }
                    // Consent form was closed.
                }

                override fun onConsentFormError(errorDescription: String) {
                    Log.d("MainActivity", "Requesting Consent: onConsentFormError. Error - $errorDescription")
                    // Consent form error.
                }
            })
            .withPersonalizedAdsOption()
            .withNonPersonalizedAdsOption()
            //.withAdFreeOption()
            .build()
        form?.load()
    }*/

    private fun showPersonalizedAds() {
        /*val adRequest = AdRequest.Builder()
            .addNetworkExtrasBundle(AdMobAdapter::class.java, getNonPersonalizedAdsBundle())
            .build()
        ConsentInformation.getInstance(context).setTagForUnderAgeOfConsent(true);

        binding.mainAdView.loadAd(AdRequest.Builder().build())
        val mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder()
            .addTestDevice("your device id from logcat") // Todo: Remove in Release build
            .build()
        mAdView.loadAd(adRequest)*/
    }

    private fun showNonPersonalizedAds() {
        /*val mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder()
            .addTestDevice("your device id from logcat") // Todo: Remove in Release build
            .addNetworkExtrasBundle(AdMobAdapter::class.java, getNonPersonalizedAdsBundle())
            .build()
        mAdView.loadAd(adRequest)*/
    }

    /*private fun showForm() {
        if (form == null) {
            Log.d("MainActivity", "Consent form is null")
        }
        if (form != null) {
            Log.d("MainActivity", "Showing consent form")
            form?.show()
        } else {
            Log.d("MainActivity", "Not Showing consent form")
        }
    }*/
}