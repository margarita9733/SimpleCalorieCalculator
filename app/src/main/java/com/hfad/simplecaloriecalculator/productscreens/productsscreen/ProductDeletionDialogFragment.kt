package com.hfad.simplecaloriecalculator.productscreens.productsscreen

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.hfad.simplecaloriecalculator.R

class ProductDeletionDialogFragment(
    private val onDeleteClicked: () -> Unit,
    private val onDismissClicked: () -> Unit
) : DialogFragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context.applicationContext)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.deletion_confirmation))
            .setPositiveButton(R.string.delete) { _, _ ->
                onDeleteClicked()
            }
            .setNegativeButton(getString(R.string.button_cancel)) { _, _ ->
                onDismissClicked()
            }
            .create()

    companion object {
        const val TAG = "ProductDeletionDialog"
    }
}
