package com.example.listedetachesapp.Entity

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listedetachesapp.R


class TachesAdapter(private val taches: MutableList<Tache>) : RecyclerView.Adapter<TachesAdapter.TacheViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TacheViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tache, parent, false)
        return TacheViewHolder(view)
    }

    override fun onBindViewHolder(holder: TacheViewHolder, position: Int) {
        val tache = taches[position]
        holder.bind(tache)
    }

    override fun getItemCount(): Int {
        return taches.size
    }

    inner class TacheViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Associez les vues de l'élément ici et définissez les écouteurs d'événements
        // Par exemple, un bouton pour marquer une tâche comme terminée.
        private val descriptionTextView: TextView = itemView.findViewById(R.id.textDescription)
        private val estTermineeCheckBox: CheckBox = itemView.findViewById(R.id.checkBoxTerminee)
        private val deleteButton: Button = itemView.findViewById(R.id.buttonDelete)

        fun bind(tache: Tache) {
            descriptionTextView.text = tache.description
            estTermineeCheckBox.isChecked = tache.estTerminee

            //Marquage de la tache comme terminee
            estTermineeCheckBox.setOnCheckedChangeListener { _, isChecked ->
                tache.estTerminee = isChecked
            }


            //suppression
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    taches.removeAt(position)
                    notifyItemRemoved(position)
                }
            }

            //modification
            itemView.setOnClickListener {
                val builder = AlertDialog.Builder(itemView.context)
                builder.setTitle("Modifier la tâche")

                val input = EditText(itemView.context)
                input.setText(tache.description)
                builder.setView(input)

                builder.setPositiveButton("Enregistrer") { _, _ ->
                    val newPosition = adapterPosition
                    if (newPosition != RecyclerView.NO_POSITION) {
                        taches[newPosition].description = input.text.toString()
                        notifyItemChanged(newPosition)
                    }
                }

                builder.setNegativeButton("Annuler") { dialog, _ ->
                    dialog.cancel()
                }

                builder.show()
            }

        }


    }
}

