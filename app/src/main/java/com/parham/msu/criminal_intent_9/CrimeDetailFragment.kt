package com.parham.msu.criminal_intent_9

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.parham.msu.criminal_intent_9.databinding.FragmentCrimeDetailBinding
import java.util.Date
import java.util.UUID

class CrimeDetailFragment : Fragment() {

    lateinit var crime: Crime
    //private lateinit var binding: FragmentCrimeDetailBinding
    private var _binding :FragmentCrimeDetailBinding? = null

         private val binding
             get() = checkNotNull(_binding) {
                 "Cannot access binding because it is null. Is the view visible"
             }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        crime = Crime(
            id = UUID.randomUUID(),
            title = "",
            date = Date(),
            isSolved = false
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCrimeDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    //wiring up views in a fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            //listener for edit text
            crimeTitle.doOnTextChanged { text, _, _, _ ->
                crime = crime.copy(title = text.toString())
            }
        //listener for button

        crimeDate.apply {
            text = crime.date.toString()
            isEnabled = false
        }

        //listener for textbox changes
            crimeSolved.setOnCheckedChangeListener { _, isChecked ->
                crime = crime.copy(isSolved = isChecked)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
            _binding = null
    }
}