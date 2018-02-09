package com.mark.business;

import java.util.ArrayList;
import java.util.List;

import com.mark.model.MovePaddle;

public class MoveBusiness {
	private static List<MovePaddle> paddleQueue;

	public MoveBusiness() {
		paddleQueue = new ArrayList<>();
	}

	public static void addToQueue(MovePaddle move) {
		paddleQueue.add(move);
	}

	public static MovePaddle getFirstInQueue() {
		if (paddleQueue.size() > 0) {
			MovePaddle move = paddleQueue.get(0);
			paddleQueue.remove(0);
			return move;
		}
		return null;
	}
}
