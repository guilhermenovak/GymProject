package br.com.gymproject.ui

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.gymproject.R
import br.com.gymproject.data.local.database.Exercise
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ExerciseBottomSheetAdapter(private val dataset: MutableList<Exercise?>) :
    RecyclerView.Adapter<ExerciseBottomSheetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.bottom_sheet_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.let {
            it.txtBottomSheetDataUser.text = dataset[position]?.name.plus(" ")
            it.txtBottomSheetDescriptionUser.text = dataset[position]?.description.plus("")
        }
    }

    override fun getItemCount(): Int =
        dataset.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtBottomSheetDataUser: TextView
        val txtBottomSheetDescriptionUser: TextView

        init {
            // Define click listener for the ViewHolder's View.
            txtBottomSheetDataUser = view.findViewById(R.id.txtBottomSheetDataUser)
            txtBottomSheetDescriptionUser = view.findViewById(R.id.txtBottomSheetDescriptionUser)
        }
    }
}