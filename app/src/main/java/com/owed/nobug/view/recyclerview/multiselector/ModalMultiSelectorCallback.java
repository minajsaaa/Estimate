package com.owed.nobug.view.recyclerview.multiselector;

import android.support.v7.view.ActionMode;
import android.view.Menu;

public abstract class ModalMultiSelectorCallback implements ActionMode.Callback {

    private MultiSelector mMultiSelector;
    private static final String TAG = "modalMultiSelectorCallback";
    private boolean mClearOnPrepare = true;

    public ModalMultiSelectorCallback(MultiSelector multiSelector) {
        mMultiSelector = multiSelector;
    }

    /**
     * <p>Get current value of clearOnPrepare.</p>
     *
     * @return Current value of clearOnPrepare.
     */
    public boolean shouldClearOnPrepare() {
        return mClearOnPrepare;
    }

    /**
     * <p>Setter for clearOnPrepare.
     * When this property is true, {@link #onPrepareActionMode(ActionMode, Menu)}
     * will call {@link MultiSelector#clearSelections()}.</p>
     *
     * @param clearOnPrepare New value for clearOnPrepare.
     */
    public void setClearOnPrepare(boolean clearOnPrepare) {
        mClearOnPrepare = clearOnPrepare;
    }

    public MultiSelector getMultiSelector() {
        return mMultiSelector;
    }

    public void setMultiSelector(MultiSelector multiSelector) {
        mMultiSelector = multiSelector;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        if (mClearOnPrepare) {
            mMultiSelector.clearSelections();
        }
        mMultiSelector.setSelectable(true);
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        mMultiSelector.setSelectable(false);
    }


}
