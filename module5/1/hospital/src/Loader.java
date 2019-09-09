import java.text.DecimalFormat;

public class Loader
{
    private static final int PATIENT_COUNT = 30;
    private static final float MIN_HELTH_TEMP = 36.2f;
    private static final float MAX_HELTH_TEMP = 36.9f;

    public static void main(String[] args) {
        float [] patientDegrees = new float[PATIENT_COUNT];
        float sumDegrees = 0;
        int healthyPatientCount = 0;
        for (int patientCount = 0; patientCount < PATIENT_COUNT; patientCount++) {
            patientDegrees[patientCount] = roundDegrees(32 + 8 * Math.random());
            if (patientDegrees[patientCount] >= MIN_HELTH_TEMP && patientDegrees[patientCount] < MAX_HELTH_TEMP) {
                healthyPatientCount ++;
            }
            sumDegrees += patientDegrees[patientCount];
        }
        float middleDegrees = roundDegrees((double) sumDegrees / PATIENT_COUNT);
        System.out.println("Средняя температура по больнице: " + middleDegrees + "\nКоличество здоровых пациентов: " + healthyPatientCount);
    }

    private static Float roundDegrees (Double notRound) {
        DecimalFormat decimalFormat = new DecimalFormat("##.#");
        String numberString = decimalFormat.format(notRound).replaceAll(",",".");
        return Float.parseFloat(numberString);
    }
}
