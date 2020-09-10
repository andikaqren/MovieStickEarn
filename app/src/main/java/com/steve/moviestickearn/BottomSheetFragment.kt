package com.steve.moviestickearn

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.steve.moviestickearn.core.domain.model.Movie
import kotlinx.android.synthetic.main.bottom_sheet.*


class BottomSheetFragment : BottomSheetDialogFragment() {

    var movie: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottom_share.setOnClickListener {
            movie?.let {
                shareContent(it)
            }
        }
        bottom_copy.setOnClickListener {
            movie?.let {
                copyClipboard(it)
            }
        }
    }

    fun copyClipboard(movie: Movie) {
        val myClipboard =
            context?.let { it.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }
        val myClip: ClipData = ClipData.newPlainText("note_copy", movie.overview)
        myClipboard?.setPrimaryClip(myClip)
        Toast.makeText(context, "Success Copy to Clipboard", Toast.LENGTH_LONG).show()
        dismiss()
    }

    fun shareContent(movie: Movie) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT, movie.overview

        )
        sendIntent.type = "text/plain"
        context?.startActivity(sendIntent)
        dismiss()
    }
}