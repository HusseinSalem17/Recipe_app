package com.hussein.yummyfood.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hussein.domain.entity.ExtendedIngredientX
import com.hussein.domain.entity.StepX
import com.hussein.yummyfood.R

class InstructionsRecipeAdapter(
    private val step: List<StepX>,
) :
    RecyclerView.Adapter<InstructionsRecipeAdapter.InstructionsRecipeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): InstructionsRecipeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_recipe_instructions, parent, false)
        return InstructionsRecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: InstructionsRecipeViewHolder, position: Int) {
        val currentItem = step[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return step.size
    }


    class InstructionsRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val instructionStep: TextView =
            itemView.findViewById(R.id.instructionsSteps)
        private val instructionsTxt: TextView = itemView.findViewById(R.id.instructionsTxt)

        fun bind(step: StepX) {
            instructionStep.text = step.number.toString()
            instructionsTxt.text = step.step
        }
    }
}