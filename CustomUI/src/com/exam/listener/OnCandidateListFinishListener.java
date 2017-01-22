package com.exam.listener;

import java.util.List;


import com.exam.bean.CandidateList.Data;

public interface OnCandidateListFinishListener {

	void onCandidateListFinished(List<Data> data);
}