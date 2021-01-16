package com.example.segundaaula

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_segunda_tela.*
import java.time.LocalDateTime

class SegundaTela : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda_tela)

        // receber as variaveis da outra tela para essa tela
        var nome:String = intent.getStringExtra("nome")
        var dia:String = intent.getStringExtra("dia")
        var ano:String = intent.getStringExtra("ano")

        // check se nao é nulo e chamar a funcao
        if (nome!=null && dia!=null && ano!=null){
            decidePassado(nome,dia,ano)
        }
    }

    // funcao para dizer o numero
    fun decidePassado(nome:String,dia:String,ano:String){
        var numero:Int = 0
        if(ano.toInt() <= 1970){
            if (nome.length < 10 && dia.toInt() >= 1 && dia.toInt() <= 15){ numero = 0 }

            if (nome.length < 10 && dia.toInt() > 15){ numero = 1 }

            if (nome.length > 10 && dia.toInt() >= 1 && dia.toInt() <= 15){ numero = 2 }

            if (nome.length > 10 && dia.toInt() > 15){ numero = 3 }
        }

        if (ano.toInt() in 1971..1995){
            if (nome.length < 10 && dia.toInt() >= 1 && dia.toInt() <= 15){ numero = 3 }

            if (nome.length < 10 && dia.toInt() > 15){ numero = 2 }

            if (nome.length > 10 && dia.toInt() >= 1 && dia.toInt() <= 15){ numero = 1 }

            if (nome.length > 10 && dia.toInt() > 15){ numero = 0 }
        }

        if (ano.toInt() > 1995){
            if (nome.length < 10 && dia.toInt() >= 1 && dia.toInt() <= 15){ numero = 2 }

            if (nome.length < 10 && dia.toInt() > 15){ numero = 1 }

            if (nome.length > 10 && dia.toInt() >= 1 && dia.toInt() <= 15){ numero = 3 }

            if (nome.length > 10 && dia.toInt() > 15){ numero = 0 }
        }

        // chama a funcao para mostrar a profissao na tela
        geraTexto(numero)
    }

    // funcao para mostrar a profissao na tela
    fun geraTexto(numero:Int){

        // lista das profissoes
        var frase:List<String> = listOf<String>("faraó","bruxo","ferreiro","samurai")

        // switch do numero para mostrar o desenho na tela
        when (numero){
            0-> personagemIMV.setImageResource(R.drawable.farao)
            1-> personagemIMV.setImageResource(R.drawable.mago)
            2-> personagemIMV.setImageResource(R.drawable.ferreiro)
            3-> personagemIMV.setImageResource(R.drawable.samurai)
            else -> personagemIMV.setImageResource(R.drawable.rei)
        }

        // mostrar a frase na tela
        val fraseTela = "Você era um ".plus(frase[numero])
        profissaoText.text = fraseTela
    }
}