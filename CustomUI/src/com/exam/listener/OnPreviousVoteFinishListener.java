package com.exam.listener;

import java.util.List;


import com.exam.bean.PreviousVote;
import com.exam.bean.PreviousVote.Data;

public interface OnPreviousVoteFinishListener {

	void onPreviousVoteFinished(PreviousVote previousVote);
}
