package br.com.gymproject.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.gymproject.R
import br.com.gymproject.data.local.database.Exercise
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ExerciseAdapter(private var dataset: MutableList<Exercise?>) :
    RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {
    val TAG = "FIRESTORE"

    private val db = Firebase.firestore

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item, viewGroup, false)

        return ViewHolder(view)
    }

    private fun goToEditExercise(context: Context, position: Int){
        val intent = Intent(context, AddExerciseActivity::class.java)
        intent.putExtra("nome", dataset[position]?.name.toString())
        intent.putExtra("descricao", dataset[position]?.description.toString())
        intent.putExtra("id", dataset[position]?.id.toString())
        intent.putExtra("image", dataset[position]?.image.toString())
        intent.putExtra("documentId", dataset[position]?.documentId.toString())
        context.startActivity(intent)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.let {
            it.txtBottomSheetDataUser.text = dataset[position]?.name.plus(" ")
            it.txtBottomSheetDescriptionUser.text = dataset[position]?.description.plus("")
            it.itemView.findViewById<ImageButton>(R.id.btnDeleteExercise).setOnClickListener {
                val builder = AlertDialog.Builder(it.context)
                // Get the layout inflater
                val inflater = LayoutInflater.from(it.context)
                val dialogView = inflater.inflate(R.layout.modal_confirmation_trash, null)
                // Inflate and set the layout for the dialog
                // Pass null as the parent view because it's going in the dialog layout
                builder.setView(dialogView)
                val dialog = builder.create()
                dialog.show()

                dialogView.findViewById<Button>(R.id.btnNaoExcluir).setOnClickListener {
                    dialog.dismiss()
                }
                dialogView.findViewById<Button>(R.id.btnExcluir).setOnClickListener{
                    db.collection("exercise").document(dataset[position]?.documentId.toString())
                        .delete()
                        .addOnSuccessListener {
                            Log.d(TAG, "DocumentSnapshot successfully deleted!")
                            dataset.removeAt(position)
                            notifyItemRemoved(position)
                            notifyDataSetChanged()
                            dialog.dismiss()
                        }
                        .addOnFailureListener {
                                e -> Log.w(TAG, "Error deleting document", e)
                            dialog.dismiss()
                        }
                }
            }
            it.itemView.findViewById<ImageButton>(R.id.btnEditExercise).setOnClickListener {
                goToEditExercise(it.context, position)
            }
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
            txtBottomSheetDescriptionUser = view.findViewById(R.id.txtBottomSheetObservationUser)
        }
    }
}