package com.example.twiga_wellnessapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {
    private var mInterstitionAd :  InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //initialize ADMOb ADS
        MobileAds.initialize(this)
        val adview = findViewById<AdView>(R.id.adview)
        val adRequest = AdRequest.Builder().build()//request ad from admob
        //display ad
        adview.loadAd(adRequest)

        //load interstitial ad
        loadinterstitionAd()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //intents
         //finding the views by id

        val healty_tips = findViewById<Button>(R.id.health_tips)
        val nutritionAdvice = findViewById<Button>(R.id.nutrition_advice)
        val medication = findViewById<Button>(R.id.medication)
        val hydration = findViewById<Button>(R.id.hydration_alert)
        val startAlert = findViewById<Button>(R.id.start_exercise)
        val dailyMotivation = findViewById<Button>(R.id.daily_motivation)
        val weeklyGoals = findViewById<Button>(R.id.weekly_goals)

        healty_tips.setOnClickListener {
            val intent = Intent(applicationContext, Healty_Tips::class.java)
            startActivity(intent)
            showInterstitialAd()
        }
        nutritionAdvice.setOnClickListener {
            val intent = Intent(applicationContext, nutritionAdvice::class.java)
            startActivity(intent)
        }
        medication.setOnClickListener {
            val intent = Intent(applicationContext, medication::class.java)
            startActivity(intent)
        }
        hydration.setOnClickListener {
            val intent = Intent(applicationContext, Hydration::class.java)
            startActivity(intent)
        }
        startAlert.setOnClickListener {
            val intent = Intent(applicationContext, startAlert::class.java)
            startActivity(intent)
        }
        dailyMotivation.setOnClickListener {
            val intent = Intent(applicationContext, dailyMotivation::class.java)
            startActivity(intent)
        }
        weeklyGoals.setOnClickListener {
            val intent = Intent(applicationContext, weeklyGoals::class.java)
            startActivity(intent)
        }
    }
    //load interstition ad (function) requesting an ad from admob
    fun loadinterstitionAd(){
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback(){
                override fun onAdLoaded(ad: InterstitialAd) {////load the AD and store it in member variable
                    mInterstitionAd = ad

                }

                override fun onAdFailedToLoad(error : LoadAdError) {//setting add to null if it fails eg.network
                    mInterstitionAd = null

                }
            }
        )
    }
    //display this ad in this activity
    fun showInterstitialAd(){
        if(mInterstitionAd != null){
            mInterstitionAd?.show(this)
        }
    }
}