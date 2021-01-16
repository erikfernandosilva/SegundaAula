package com.example.segundaaula

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.ads.*
import com.google.android.gms.ads.MobileAds.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // chama a funcao para criar o ad
        criarAd()

        buttonProximo.setOnClickListener {

            //pegar os valores das variaveis
            var nome: EditText = findViewById(R.id.editName)
            var nasc: EditText = findViewById(R.id.editDate)
            var ano: EditText = findViewById(R.id.editDate2)

            // anuncios das toasts sobre valores vazios
            if (nome.text.toString() == null || nome.text.toString() == ""){
                Toast.makeText(this,"o nome não pode ser vazio",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (nasc.text.toString() == null || nasc.text.toString() == ""){
                Toast.makeText(this,"o dia não pode ser vazio",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (ano.text.toString() == null || ano.text.toString() == ""){
                Toast.makeText(this,"o ano não pode ser vazio",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // passar esses valores para a segunda tela
            val intent = Intent(this,SegundaTela::class.java)
            intent.putExtra("nome",nome.text.toString())
            intent.putExtra("dia",nasc.text.toString())
            intent.putExtra("ano",ano.text.toString())

            // mostrar o anuncio
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            }

            // sistemas de comportamentos para a ad
            mInterstitialAd.adListener = object: AdListener() {
                override fun onAdLoaded() { // Code to be executed when an ad finishes loading.
                }
                override fun onAdFailedToLoad(adError: LoadAdError) { // Code to be executed when an ad request fails.
                }
                override fun onAdOpened() { // Code to be executed when the ad is displayed.
                }
                override fun onAdClicked() { // Code to be executed when the user clicks on an ad.
                }
                override fun onAdLeftApplication() { // Code to be executed when the user has left the app.
                }
                override fun onAdClosed() {
                    // codigo que executa na hora que o codigo é executado
                    // cria o outro ad e joga a atividade
                    criarAd()
                    startActivity(intent)
                }
            }
        }
    }

    // funcao para criar o ad
    private fun criarAd() {
        MobileAds.initialize(this) {}
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd.loadAd(AdRequest.Builder().build())
    }
}