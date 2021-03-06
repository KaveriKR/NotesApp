package com.kaverikr.notesapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kaverikr.notesapp.R
import com.kaverikr.notesapp.data.ToDoViewModel
import com.kaverikr.notesapp.data.models.Priority
import com.kaverikr.notesapp.data.models.ToDoData
import kotlinx.android.synthetic.main.fragment_add.*


class AddFragment : Fragment() {


    private  val mToDoViewModel : ToDoViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_add, container, false)

         setHasOptionsMenu(true)
         return view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_add){
            insertDataToDao()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDao() {
        val mTitle = title_et.text.toString()
        val mPriority = priorities_spinner.selectedItem.toString()
        val mDescription = description_et.text.toString()

        val validation = verifyDataFromUser(mTitle, mDescription)
        if(validation){
            val newData = ToDoData(
                0,
                mTitle,
                parsePriority(mPriority),
                mDescription
            )

            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Data Inserted",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    private  fun verifyDataFromUser(title: String, discription: String):Boolean{
        return if(TextUtils.isEmpty(title) || TextUtils.isEmpty(discription)){
            false
        }else !(title.isEmpty() || discription.isEmpty())
    }

    private fun parsePriority(priority:String):Priority{

        return when(priority){

            "High Priority" -> {Priority.HIGH}
            "Medium Priority" -> {Priority.MEDIUM}
            "Low Priority" -> {Priority.LOW}
            else -> Priority.LOW

        }
    }
}