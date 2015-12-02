// Copyright 2004-present Facebook. All Rights Reserved.

package com.jf_eam_project.api.ig.json;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import com.jf_eam_project.api.ig.json.impl.PO_JsonHelper;
import com.jf_eam_project.model.Po;
import com.jf_eam_project.model.WorkOrder;

/**
 * Helper class to parse the model.
 *
 */
public class Ig_Json_Model {
    private static final String TAG = "Ig_Json_Model";

    /**²É¹º¶©µ¥**/
    public static ArrayList<Po> parseFromString(String input) throws IOException {
        Log.i(TAG, "input=" + input);
        return PO_JsonHelper.parseFromJsonList(input);
    }


}
