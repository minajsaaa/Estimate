package com.owed.nobug.view.recyclerview.multiselector;

import android.os.Bundle;
import android.util.SparseBooleanArray;

import java.util.ArrayList;
import java.util.List;

public class MultiSelector {
    private static final String SELECTION_POSITIONS = "position";
    private static final String SELECTIONS_STATE = "state";
    private SparseBooleanArray mSelections = new SparseBooleanArray();
    private WeakHolderTracker mTracker = new WeakHolderTracker();
    private boolean mIsSelectable;

    public MultiSelector() {
    }

    /**
     * <p>Current value of selectable.</p>
     *
     * @return True if in selection mode.
     */
    public boolean isSelectable() {
        return mIsSelectable;
    }

    public void setSelectable(boolean isSelectable) {
        mIsSelectable = isSelectable;
        refreshAllHolders();
    }

    /**
     * <p>Calls through to {@link #setSelected(int, long, boolean)}.</p>
     *
     * @param holder     Holder to set selection value for.
     * @param isSelected Whether the item should be selected.
     */
    public void setSelected(SelectableHolder holder, boolean isSelected) {
        setSelected(holder.getAdapterPosition(), holder.getItemId(), isSelected);
    }

    public void setSelected(int position, long id, boolean isSelected) {
        mSelections.put(position, isSelected);
        refreshHolder(mTracker.getHolder(position));
    }

    /**
     * <p>Returns whether a particular item is selected.</p>
     *
     * @param position The position to test selection for.
     * @param id       Item id to select/unselect. Ignored in this implementation.
     * @return Whether the item is selected.
     */
    public boolean isSelected(int position, long id) {
        return mSelections.get(position);
    }

    /**
     * <p>Sets selected to false for all positions. Will refresh
     * all bound holders.</p>
     */
    public void clearSelections() {
        mSelections.clear();
        refreshAllHolders();
    }

    /**
     * <p>Return a list of selected positions.</p>
     *
     * @return A list of the currently selected positions.
     */
    public List<Integer> getSelectedPositions() {
        List<Integer> positions = new ArrayList<Integer>();

        for (int i = 0; i < mSelections.size(); i++) {
            if (mSelections.valueAt(i)) {
                positions.add(mSelections.keyAt(i));
            }
        }

        return positions;
    }

    public void bindHolder(SelectableHolder holder, int position, long id) {
        mTracker.bindHolder(holder, position);
        refreshHolder(holder);
    }

    /**
     * <p>Calls through to {@link #tapSelection(int, long)}.</p>
     *
     * @param holder The holder to tap.
     * @return True if {@link #isSelectable()} and selection was toggled for this item.
     */
    public boolean tapSelection(SelectableHolder holder) {
        return tapSelection(holder.getAdapterPosition(), holder.getItemId());
    }

    public boolean tapSelection(int position, long itemId) {
        if (mIsSelectable) {
            boolean isSelected = isSelected(position, itemId);
            setSelected(position, itemId, !isSelected);
            return true;
        } else {
            return false;
        }

    }

    public void refreshAllHolders() {
        for (SelectableHolder holder : mTracker.getTrackedHolders()) {
            refreshHolder(holder);
        }
    }

    private void refreshHolder(SelectableHolder holder) {
        if (holder == null) {
            return;
        }
        holder.setSelectable(mIsSelectable);

        boolean isActivated = mSelections.get(holder.getAdapterPosition());
        holder.setActivated(isActivated);
    }


    /**
     * @return Bundle containing the states of the selection and a flag indicating if the multiselection is in
     * selection mode or not
     */

    public Bundle saveSelectionStates() {
        Bundle information = new Bundle();
        information.putIntegerArrayList(SELECTION_POSITIONS, (ArrayList<Integer>) getSelectedPositions());
        information.putBoolean(SELECTIONS_STATE, isSelectable());
        return information;
    }

    /**
     * restore the selection states of the multiselector and the ViewHolder Trackers
     *
     * @param savedStates Saved state bundle, probably from a fragment or activity.
     */

    public void restoreSelectionStates(Bundle savedStates) {
        List<Integer> selectedPositions = savedStates.getIntegerArrayList(SELECTION_POSITIONS);
        restoreSelections(selectedPositions);
        mIsSelectable = savedStates.getBoolean(SELECTIONS_STATE);

    }

    private void restoreSelections(List<Integer> selected) {
        if (selected == null) return;
        int position;
        mSelections.clear();
        for (int i = 0; i < selected.size(); i++) {
            position = selected.get(i);
            mSelections.put(position, true);
        }
        refreshAllHolders();
    }


}
