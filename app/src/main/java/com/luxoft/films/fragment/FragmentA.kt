package com.luxoft.films.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import com.luxoft.films.R
import com.luxoft.films.dto.PersonJavaTest
import com.luxoft.films.dto.Pet
import com.luxoft.films.viewmodel.SharedViewModel


class FragmentA : Fragment() {

    private val model: SharedViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_a, container, false)
        val txtView = root.findViewById<TextView>(R.id.text_fragmentA)
        val btnView = root.findViewById<Button>(R.id.btn_fragmentA)

        model.getNameData()?.observe(viewLifecycleOwner, Observer<String> { item ->
            txtView.text = item
        })

        val myPerson = PersonJavaTest()
        myPerson.name = "Marco"
        myPerson.age = 31
        print(myPerson.name)

        val test = PersonJavaTest.myJavaFunction()
        print(test)

        val myPet = Pet()
        myPet.name = "Foo"
        val petName = myPet.name
        print(petName)

        /*Sets whether or not to allow optimizing operations within and across transactions.
            setReorderingAllowed(true)
            This will remove redundant operations, eliminating operations that cancel. For example,
            if two transactions are executed together, one that adds a fragment A and the next replaces it with fragment B,
            the operations will cancel and only fragment B
        */


        btnView.setOnClickListener {
            //Send data to Fragment B
            val bundle = bundleOf("key" to "Hello World!")
            activity?.supportFragmentManager?.commit { //Commit is asynchronous
                setReorderingAllowed(true)
                addToBackStack(null)
                replace<FragmentB>(R.id.fragment_container_view, args = bundle, tag = "fragmentB")
            }
        }
        return root
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }
}