package com.eman.readsvg

//import org.apache.batik.anim.dom.SAXSVGDocumentFactory
//import org.apache.batik.util.XMLResourceDescriptor
//import org.w3c.dom.Element
//import org.w3c.dom.Node
//import org.w3c.dom.NodeList
//import java.io.File

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eman.readsvg.databinding.ActivityMainBinding
import java.io.IOException
import javax.xml.parsers.DocumentBuilderFactory


class MainActivity : AppCompatActivity() {
    private var doors = mutableListOf<ModelDoor>()
    lateinit var binding: ActivityMainBinding
    var c = Array(6) { "" }
    private lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        adapter = MainAdapter(arrayListOf())
        binding.mainAdapter = adapter
        listAssetFiles("")

//        getElementDoors("MapTest.svg", "DOORS")

    }

    private fun listAssetFiles(path: String) {
        val list = assets.list(path)
        if (list!!.size > 0) {
            for (file in list) {
                if (("$path/$file").contains(".svg")) {
                    doors = mutableListOf()
                    val path = ("$path/$file").split("/")[1]
                    getElementDoors(path, "DOORS")
                }
            }
        }
    }

    private fun getElementDoors(pathFile: String, elementId: String) {
        try {
            val ids = getAssets().open(pathFile)
            val dbFactory = DocumentBuilderFactory.newInstance()
            val dBuilder = dbFactory.newDocumentBuilder()
            val doc = dBuilder.parse(ids)

            val element = doc.getDocumentElement()
            element.normalize()

            val nList = doc.getElementById(elementId)

            for (i in 0..nList.childNodes.length - 1) {
                if (nList.childNodes.item(i).hasAttributes()) {
                    for (j in 0..nList.childNodes.item(i).attributes.length - 1) {
                        val value = nList.childNodes.item(i).attributes.item(j).nodeValue.toString()
                        c.set(j, value)
                    }
                    doors.add(ModelDoor(c.get(0), c.get(1), c.get(2), c.get(3), c.get(4), c.get(5)))
                }
            }
            adapter.addData(doors)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}
