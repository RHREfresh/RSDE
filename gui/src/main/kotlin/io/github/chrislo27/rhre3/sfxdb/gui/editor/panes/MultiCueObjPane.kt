package io.github.chrislo27.rhre3.sfxdb.gui.editor.panes

import io.github.chrislo27.rhre3.sfxdb.adt.CuePointer
import io.github.chrislo27.rhre3.sfxdb.adt.MultiCue
import io.github.chrislo27.rhre3.sfxdb.gui.editor.Editor
import io.github.chrislo27.rhre3.sfxdb.gui.util.bindLocalized
import io.github.chrislo27.rhre3.sfxdb.gui.util.doubleSpinnerFactory
import io.github.chrislo27.rhre3.sfxdb.gui.validation.Validators
import javafx.scene.control.CheckBox
import javafx.scene.control.Label
import javafx.scene.control.Tooltip


class MultiCueObjPane(editor: Editor, struct: MultiCue) : MultipartStructPane<MultiCue>(editor, struct) {

    val durationField = doubleSpinnerFactory(0.0, Float.MAX_VALUE.toDouble(), struct.duration.toDouble(), 0.5)
    val heightField = doubleSpinnerFactory(1.0, Float.MAX_VALUE.toDouble(), struct.height.toDouble(), 1.0)
    val stretchableField = CheckBox().apply { this.isSelected = struct.stretchable }
    val canSplitField = CheckBox().apply { this.isSelected = struct.canSplit }
    val shouldCropField = CheckBox().apply { this.isSelected = struct.shouldCrop }
    val minLengthField = doubleSpinnerFactory(0.125, Float.MAX_VALUE.toDouble(), struct.minLength.toDouble(), 0.5)
    val maxLengthField = doubleSpinnerFactory(0.0, Float.MAX_VALUE.toDouble(), struct.maxLength.toDouble(), 0.5)
    override val cuesPane: CuesPane<MultiCue> = CuesPane(this) { pointer, pane -> MultiCueCuePointerPane(pointer, pane) }

    init {
        addProperty(Label().bindLocalized("datamodel.type"), Label("multiCue").apply { styleClass += "monospaced" })
        addProperty(Label().bindLocalized("datamodel.id"), idField)
        addProperty(Label().bindLocalized("datamodel.name"), nameField)
        addProperty(Label().bindLocalized("datamodel.duration"), durationField)
        addProperty(Label().bindLocalized("datamodel.height"), heightField)
        addProperty(Label().bindLocalized("datamodel.deprecatedIDs").apply {
            tooltip = Tooltip().bindLocalized("datamodel.deprecatedIDs.tooltip")
        }, deprecatedIDsField)
        addProperty(Label().bindLocalized("datamodel.subtext").apply {
            tooltip = Tooltip().bindLocalized("datamodel.subtext.tooltip")
        }, subtextField)

        addProperty(Label().bindLocalized("datamodel.stretchable").apply {
            tooltip = Tooltip().bindLocalized("datamodel.stretchable.tooltip")
        }, stretchableField)

        addProperty(Label().bindLocalized("datamodel.canSplit").apply {
            tooltip = Tooltip().bindLocalized("datamodel.canSplit.tooltip")
        }, canSplitField)

        addProperty(Label().bindLocalized("datamodel.shouldCrop").apply {
            tooltip = Tooltip().bindLocalized("datamodel.shouldCrop.tooltip")
        }, shouldCropField)

        addProperty(Label().bindLocalized("datamodel.minLength").apply {
            tooltip = Tooltip().bindLocalized("datamodel.minLength.tooltip")
        }, minLengthField)
        addProperty(Label().bindLocalized("datamodel.maxLength").apply {
            tooltip = Tooltip().bindLocalized("datamodel.maxLength.tooltip")
        }, maxLengthField)

        centreVbox.children += cuesPane
    }

    init {
        // Bind to struct
        durationField.valueProperty().addListener { _, _, newValue ->
            struct.duration = newValue.toFloat()
            editor.markDirty()
        }
        heightField.valueProperty().addListener { _, _, newValue ->
            struct.height = newValue.toFloat()
            editor.markDirty()
        }
        stretchableField.selectedProperty().addListener { _, _, newValue ->
            struct.stretchable = newValue
            editor.markDirty()
        }
        canSplitField.selectedProperty().addListener { _, _, newValue ->
            struct.canSplit = newValue
            editor.markDirty()
        }
        shouldCropField.selectedProperty().addListener { _, _, newValue ->
            struct.shouldCrop = newValue
            editor.markDirty()
        }
        minLengthField.valueProperty().addListener { _, _, newValue ->
            struct.minLength = newValue.toFloat()
            editor.markDirty()
        }
        maxLengthField.valueProperty().addListener { _, _, newValue ->
            struct.maxLength = newValue.toFloat()
            editor.markDirty()
        }
    }

    init {
        // Validators
        validation.registerValidators(idField, Validators.OBJ_ID_BLANK, Validators.OBJ_ID_REGEX, Validators.OBJ_ID_STAR_SUB, Validators.identicalObjID(editor.gameObject, this.struct))
        validation.registerValidator(nameField, Validators.NAME_BLANK)
    }

    class MultiCueCuePointerPane(cuePointer: CuePointer, parent: CuesPane<MultiCue>) : CuePointerPane<MultiCue>(parent, cuePointer) {
        init {
            addProperty(Label().bindLocalized("cuePointer.id"), idField)
            addProperty(Label().bindLocalized("cuePointer.beat"), beatField)
            addProperty(Label().bindLocalized("cuePointer.duration").apply {
                tooltip = Tooltip().bindLocalized("cuePointer.duration.tooltip")
            }, durationField)
            addProperty(Label().bindLocalized("cuePointer.semitone"), semitoneField)
            addProperty(Label().bindLocalized("cuePointer.track").apply {
                tooltip = Tooltip().bindLocalized("cuePointer.track.tooltip")
            }, trackField)
            addProperty(Label().bindLocalized("cuePointer.volume"), volumeField)
            addProperty(Label().bindLocalized("cuePointer.ignorePitching").apply {
                tooltip = Tooltip().bindLocalized("cuePointer.ignorePitching.tooltip")
            }, ignorePitchingField)
            addProperty(Label().bindLocalized("cuePointer.stretchBehavior").apply {
                tooltip = Tooltip().bindLocalized("cuePointer.stretchBehavior.tooltip")
            }, stretchBehaviorComboBox)
        }
    }

}