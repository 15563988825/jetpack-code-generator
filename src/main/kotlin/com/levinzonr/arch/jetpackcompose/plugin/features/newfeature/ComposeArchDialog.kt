package com.levinzonr.arch.jetpackcompose.plugin.features.newfeature

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.layout.panel
import com.levinzonr.arch.jetpackcompose.plugin.core.BaseDialog
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ComposeArchDialog(
    private val viewModel: ComposeArchDialogViewModel,
) : BaseDialog() {

    init {
        init()
        viewModel
                .successFlow
                .onEach { close(0) }
                .launchIn(dialogScope)
    }

    override fun createPanel(): DialogPanel {
        return panel {
            row { label("New Jetpack Compose Feature") }
            row { textField(viewModel::name).focused() }
            row { checkBox("Also create package for the feature", viewModel::createFeaturePackage) }

            noteRow("创建鼎新通信的ComposeModule创建 Activity+ViewModel")
        }
    }

    override fun doOKAction() {
        panel.apply()
        viewModel.onOkButtonClick()
    }
}