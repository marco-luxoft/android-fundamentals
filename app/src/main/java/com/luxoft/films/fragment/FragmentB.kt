package com.luxoft.films.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.luxoft.films.R
import com.luxoft.films.viewmodel.FragmentBViewModel
import com.luxoft.films.viewmodel.SharedViewModel

class FragmentB : Fragment() {

    private val model: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val aValueFromFragmentA = requireArguments().getString("key")
        val root = inflater.inflate(R.layout.fragment_b, container, false)
        val txtView = root.findViewById<TextView>(R.id.text_fragmentB)
        txtView.text = aValueFromFragmentA
        val btnView = root.findViewById<Button>(R.id.btn_fragmentB)
        val closeFragment = root.findViewById<Button>(R.id.btn_close_fragment)
        val fragmentViewModel = ViewModelProvider(this).get(FragmentBViewModel::class.java)
        btnView.setOnClickListener {
            model.setNameData("From fragment B")
        }

        closeFragment.setOnClickListener {
            val count = activity?.supportFragmentManager?.backStackEntryCount
            if (count != null) {
                if (count > 0) {
                    activity?.supportFragmentManager?.popBackStack()
                }
            }

            /*val fragmentA = activity?.supportFragmentManager?.findFragmentByTag("fragmentA")
            fragmentA?.let {
                    activity?.supportFragmentManager?.commit {
                        setReorderingAllowed(true)
                        //addToBackStack(null) //Avoid this
                        replace(R.id.fragment_container_view, it, "fragmentA")
                }
            }*/
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }
}