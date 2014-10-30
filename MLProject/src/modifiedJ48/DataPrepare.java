package modifiedJ48;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import weka.core.Instances;

public class DataPrepare {
	
	public boolean notInList(int indicator, int[] randNumberList) {
		for (int i=0; i < randNumberList.length; i++) {
			if (randNumberList[i] == indicator) {
				return false;
			}
		}
		return true;
	}

	public Instances getTrainData(Instances data, int [] randNumberList) {
		
		Instances train = new Instances(data);
		train.delete();
		
		//Prepare train data set according to randList index sequence
		for (int i=0; i < data.numInstances(); i++){
			train.add(data.instance(randNumberList[i]));
		}
		return train;
	}

	// preparing test data 
	public Instances getTestData(Instances data, int[] randNumberList) {
		Instances test = new Instances(data); 
		test.delete();
		
		for (int i=0; i < data.numInstances(); i++) { 
			if (notInList(i,randNumberList)) {
				test.add(data.instance(i));
			}
		}
		return test;
	}
	
	public int[] getRandomNumberList(Instances data) {
		Random randNumberGenerator = new Random();
		int [] randNumberList =new int [data.numInstances()];
		for (int i=0; i < data.numInstances(); i++){
			randNumberList[i] = randNumberGenerator.nextInt(data.numInstances());
		}
		return randNumberList;
	}
	
	/*public void writeCSV(String fileName, String val) throws IOException {
		try { 
			FileWriter writer = new FileWriter(fileName, true);
			BufferedWriter out = new BufferedWriter(writer);
			
			out.write(val);
			out.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}*/
}
