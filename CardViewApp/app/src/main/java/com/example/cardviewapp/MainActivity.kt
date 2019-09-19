package com.example.cardviewapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {
    //cria uma array com os links da imagem online
    val listLinks: Array<String> =
        arrayOf("https://gurudacidade.com.br/wpcontent/uploads/2019/03/batman-foto-space-ca.jpg","https://www.comboinfinito.com.br/principal/wpcontent/uploads/2018/05/Arena-of-Valor-Flash.jpg","http://br.web.img3.acsta.net/r_640_360/newsv7/18/03/23/22/16/2679537.jpg","http://br.web.img2.acsta.net/r_640_360/newsv7/19/05/29/22/18/3338499.jpg")
    override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)
                //bind o recycler view
                val recyclerView = RcView
                //adiciona o adpter ao recycler
                recyclerView.adapter = CardAdpter(cards(), this)
                //cria um layout para o recycler, com 1 item por vez e na vertical
                val layoutManager = StaggeredGridLayoutManager(1,
                    StaggeredGridLayoutManager.VERTICAL)
                //adciona o layout no recycler
                recyclerView.layoutManager = layoutManager
            }
                    //cria uma lista de objetos para usar no adpter
                    private fun cards(): List<CardClass> {
                return listOf(
                    CardClass("Batman",listLinks[0]),
                    CardClass("Flash",listLinks[1]),
                    CardClass("Wonder Woman",listLinks[2]),
                    CardClass("black widow",listLinks[3])
                )
            }
}
