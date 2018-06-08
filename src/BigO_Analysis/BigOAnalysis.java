package BigO_Analysis;

import java.util.*;
import java.io.*;

public class BigOAnalysis {

    public static String timeComplexityMethod(String iVar, String jVar, boolean inputFirstLoopConstant, boolean inputFirstLoopN, boolean inputFirstLoopLogN, boolean inputFirstLoopSqrtN, boolean inputSecondLoopConstant, boolean inputSecondLoopN, boolean inputSecondLoopLogN, boolean inputSecondLoopSqrtN, boolean inputNoSecondLoop) {
        String tC = new String();
        System.out.println("Setting time complexity into bigO format.");
        //Failsafe statements: Slows down program but it's negligible makes sure output is correct
        iVar = iVar.replaceAll("\\s", "");
        if (jVar != null) {
            jVar = jVar.replaceAll("\\s", "");
        }

        //extra catch statements
        if (jVar != null) {
            if (jVar.contains("i")) {
                jVar = jVar.replace("i", iVar);
            }
        }

        if (iVar.contains("=")) {
            iVar = iVar.replace("=", "");
        }

        if (jVar != null) {
            if (jVar.contains("=")) {
                jVar = jVar.replace("=", "");
            }
        }

        //remove numerical inputs
        String[] s = {"*1", "*2", "*3", "*4", "*5", "*6", "*7", "*8", "*9", "0*", "1*", "2*", "3*", "4*", "5*", "6*", "7*", "8*", "9*", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (String item : s) {
            if (iVar.contains(item)) {
                iVar = iVar.replace(item, "");
            }
        }

        if (jVar != null) {
            for (String item : s) {
                if (jVar.contains(item)) {
                    jVar = jVar.replace(item, "");
                }
            }
        }
        //assign tC
        if (inputFirstLoopConstant == true && inputNoSecondLoop) {
            tC = "1";
        } else if (inputFirstLoopN == true && inputNoSecondLoop == true) {
            tC = iVar;
        } else if (inputFirstLoopLogN == true && inputNoSecondLoop == true) {
            tC = "log(" + iVar + ")";
        } else if (inputFirstLoopSqrtN == true && inputNoSecondLoop == true) {
            tC = "sqrt(" + iVar + ")";
        } else if (inputFirstLoopLogN == true && inputNoSecondLoop == true) {
            tC = "log(" + iVar + ")";
        } else if (inputFirstLoopN == true && inputSecondLoopConstant == true) {
            tC = iVar;
        } else if (inputFirstLoopN == true && inputSecondLoopN == true) {
            if (iVar.equals(jVar)) {
                tC = iVar + "^2";
            } else {
                tC = iVar + "*" + jVar;
            }
        } else if (inputFirstLoopN == true && inputSecondLoopLogN == true) {
            tC = iVar + "*log(" + jVar + ")";
        } else if (inputFirstLoopN == true && inputSecondLoopSqrtN == true) {
            tC = iVar + "*sqrt(" + jVar + ")";
        } else if (inputFirstLoopLogN == true && inputSecondLoopConstant == true) {
            tC = "log(" + iVar + ")";
        } else if (inputFirstLoopLogN == true && inputSecondLoopN == true) {
            tC = "log(" + iVar + ")*" + jVar;
        } else if (inputFirstLoopLogN == true && inputSecondLoopLogN == true) {
            tC = "log(" + iVar + ")*log(" + jVar + ")";
        } else if (inputFirstLoopLogN == true && inputSecondLoopSqrtN == true) {
            tC = "log(" + iVar + ")*sqrt(" + jVar + ")";
        } else if (inputFirstLoopSqrtN == true && inputSecondLoopConstant == true) {
            tC = "sqrt(" + iVar + ")";
        } else if (inputFirstLoopSqrtN == true && inputSecondLoopN == true) {
            tC = "sqrt(" + iVar + ")*" + jVar;
        } else if (inputFirstLoopSqrtN == true && inputSecondLoopLogN == true) {
            tC = "sqrt(" + iVar + ")*log(" + jVar + ")";
        } else if (inputFirstLoopSqrtN == true && inputSecondLoopSqrtN == true) {
            if (iVar.equals(jVar)) {
                tC = iVar;
            } else {
                tC = "sqrt(" + iVar + ")*sqrt(" + jVar + ")";
            }
        } else if (inputFirstLoopConstant == true && inputSecondLoopConstant == true) {
            tC = "1";
        } else if (inputFirstLoopConstant == true && inputSecondLoopN == true) {
            tC = jVar;
        } else if (inputFirstLoopConstant == true && inputSecondLoopLogN == true) {
            tC = "log(" + jVar + ")";
        } else if (inputFirstLoopConstant == true && inputSecondLoopSqrtN == true) {
            tC = "sqrt(" + jVar + ")";
        }
        return tC;
    }

    public static void printToOutputFile(File f, String tC, String directory) {
        String outputFileName = "BigO_" + f.getName();
        File newOutput = new File(directory + "\\" + outputFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newOutput))) {
            writer.write("O(" + tC + ")");
            writer.close();
        } catch (IOException e) {
            System.out.println("OUTPUT FILE WRITER ERROR");
        }
    }

    public static void printToOutputFile_CannotBeAnalyzed(File f, String directory) {
        String outputFileName = "BigO_" + f.getName();
        File newOutput = new File(directory + "\\" + outputFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newOutput))) {
            writer.write("Cannot Be Analyzed");
            writer.close();
        } catch (IOException e) {
            System.out.println("OUTPUT FILE CBA_WRITER ERROR");
        }
    }

    public static String iCaptureVar(File f) throws FileNotFoundException, IOException {
        try (BufferedReader captureReader = new BufferedReader(new FileReader(f))) {
            String capturedVar;
            String lineCapture = captureReader.readLine();
            while (lineCapture != null) {
                lineCapture = lineCapture.replaceAll("\\s", "");
                if (lineCapture.contains("i*i>=")) {
                    capturedVar = lineCapture.replaceAll(".*\\>=|\\;.*", "");
                    return capturedVar;
                } else if (lineCapture.contains("i*i<=")) {
                    capturedVar = lineCapture.replaceAll(".*\\<=|\\;.*", "");
                    return capturedVar;
                } else if (lineCapture.contains("i*i<")) {
                    capturedVar = lineCapture.replaceAll(".*\\<|\\;.*", "");
                    return capturedVar;
                } else if (lineCapture.contains("i*i>")) {
                    capturedVar = lineCapture.replaceAll(".*\\>|\\;.*", "");
                    return capturedVar;
                } else if (lineCapture.contains("i<")) {
                    capturedVar = lineCapture.replaceAll(".*\\<|\\;.*", "");
                    return capturedVar;
                } else if (lineCapture.contains("i>")) {
                    capturedVar = lineCapture.replaceAll(".*\\>|\\;.*", "");
                    return capturedVar;
                } else if (lineCapture.contains("i<=")) {
                    capturedVar = lineCapture.replaceAll(".*\\<=|\\;.*", "");
                    return capturedVar;
                } else if (lineCapture.contains("i>=")) {
                    capturedVar = lineCapture.replaceAll(".*\\>=|\\;.*", "");
                    return capturedVar;
                }
                lineCapture = captureReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("IVAR CAPTURE ERROR");
        }
        return null;
    }

    public static String jCaptureVar(File f) throws FileNotFoundException, IOException {
        try (BufferedReader captureReader = new BufferedReader(new FileReader(f))) {
            String capturedVar;
            String lineCapture = captureReader.readLine();
            while (lineCapture != null) {
                lineCapture = lineCapture.replaceAll("\\s", "");
                if (lineCapture.contains("j*j>=")) {
                    capturedVar = lineCapture.replaceAll(".*\\>=|\\;.*", "");
                    return capturedVar;
                } else if (lineCapture.contains("j*j<=")) {
                    capturedVar = lineCapture.replaceAll(".*\\<=|\\;.*", "");
                    return capturedVar;
                } else if (lineCapture.contains("j*j<")) {
                    capturedVar = lineCapture.replaceAll(".*\\<|\\;.*", "");
                    return capturedVar;
                } else if (lineCapture.contains("j*j>")) {
                    capturedVar = lineCapture.replaceAll(".*\\>|\\;.*", "");
                    return capturedVar;
                } else if (lineCapture.contains("j<")) {
                    capturedVar = lineCapture.replaceAll(".*\\<|\\;.*", "");
                    return capturedVar;
                } else if (lineCapture.contains("j>")) {
                    capturedVar = lineCapture.replaceAll(".*\\>|\\;.*", "");
                    return capturedVar;
                } else if (lineCapture.contains("j<=")) {
                    capturedVar = lineCapture.replaceAll(".*\\<=|\\;.*", "");
                    return capturedVar;
                } else if (lineCapture.contains("j>=")) {
                    capturedVar = lineCapture.replaceAll(".*\\>=|\\;.*", "");
                    return capturedVar;
                }
                lineCapture = captureReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("JVAR CAPTURE ERROR");
        }
        return null;
    }

    public static void depthAnalyze(File f, String directory) {
        boolean firstLoopAnalyzed = false;
        boolean secondLoopAnalyzed = false;

        boolean firstLoopConstant = false;
        boolean firstLoopN = false;
        boolean firstLoopLogN = false;
        boolean firstLoopSqrtN = false;

        boolean secondLoopConstant = false;
        boolean secondLoopN = false;
        boolean secondLoopLogN = false;
        boolean secondLoopSqrtN = false;
        boolean noSecondLoop = true;

        boolean cannotAnalyze = false;

        System.out.println("Creating reader for " + f.getName() + ".");
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            reader.mark(4096);
            System.out.println("Analyzing...");

            String varI = iCaptureVar(f);

            String varJ = jCaptureVar(f);

            //Special Case 1: First Loop Linear
            String lineSpecial1 = reader.readLine();
            while (lineSpecial1 != null) {
                lineSpecial1 = lineSpecial1.replaceAll("\\s", "");
                if (lineSpecial1.contains("i<i")) {
                    firstLoopConstant = true;
                    firstLoopAnalyzed = true;
                } else if (lineSpecial1.contains("i>i")) {
                    firstLoopConstant = true;
                    firstLoopAnalyzed = true;
                } else if (lineSpecial1.contains("i<=i")) {
                    firstLoopConstant = true;
                    firstLoopAnalyzed = true;
                } else if (lineSpecial1.contains("i>=i")) {
                    firstLoopConstant = true;
                    firstLoopAnalyzed = true;
                }
                lineSpecial1 = reader.readLine();
            }
            reader.reset();
            //Special Case 2: Second Loop Linear
            String lineSpecial2 = reader.readLine();
            while (lineSpecial2 != null) {
                lineSpecial2 = lineSpecial2.replaceAll("\\s", "");
                if (lineSpecial2.contains("j<j")) {
                    noSecondLoop = false;
                    secondLoopConstant = true;
                    secondLoopAnalyzed = true;
                } else if (lineSpecial2.contains("j>j")) {
                    noSecondLoop = false;
                    secondLoopConstant = true;
                    secondLoopAnalyzed = true;
                } else if (lineSpecial2.contains("j<=j")) {
                    noSecondLoop = false;
                    secondLoopConstant = true;
                    secondLoopAnalyzed = true;
                } else if (lineSpecial2.contains("j>=j")) {
                    noSecondLoop = false;
                    secondLoopConstant = true;
                    secondLoopAnalyzed = true;
                }
                lineSpecial2 = reader.readLine();
            }
            reader.reset();
            //Special Case 3: Sqrt
            String lineSpecial3 = reader.readLine();
            while (lineSpecial3 != null) {
                lineSpecial3 = lineSpecial3.replaceAll("\\s", "");
                if (lineSpecial3.contains("i*i")) {
                    if (firstLoopAnalyzed == false) {
                        firstLoopSqrtN = true;
                        firstLoopAnalyzed = true;
                    }
                    while (lineSpecial3 != null) {
                        lineSpecial3 = lineSpecial3.replaceAll("\\s", "");
                        if (lineSpecial3.contains("j*j")) {
                            noSecondLoop = false;
                            secondLoopSqrtN = true;
                            secondLoopAnalyzed = true;
                        }
                        lineSpecial3 = reader.readLine();
                    }
                }
                lineSpecial3 = reader.readLine();
            }
            reader.reset();
            //Special Case 4: Cannot Be Analyzed
            String lineSpecial4 = reader.readLine();
            while (lineSpecial4 != null) {
                lineSpecial4 = lineSpecial4.replaceAll("\\s", "");
                if (lineSpecial4.contains("i*=i")) {
                    while (lineSpecial4 != null) {
                        lineSpecial4 = lineSpecial4.replaceAll("\\s", "");
                        if (lineSpecial4.contains("j<") && lineSpecial4.contains("i")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            cannotAnalyze = true;
                        } else if (lineSpecial4.contains("j>") && lineSpecial4.contains("i")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            cannotAnalyze = true;
                        } else if (lineSpecial4.contains("j<=") && lineSpecial4.contains("i")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            cannotAnalyze = true;
                        } else if (lineSpecial4.contains("j>=") && lineSpecial4.contains("i")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            cannotAnalyze = true;
                        }
                        lineSpecial4 = reader.readLine();
                    }
                } else if (lineSpecial4.contains("i/=i")) {
                    while (lineSpecial4 != null) {
                        lineSpecial4 = lineSpecial4.replaceAll("\\s", "");
                        if (lineSpecial4.contains("j<") && lineSpecial4.contains("i")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            cannotAnalyze = true;
                        } else if (lineSpecial4.contains("j>") && lineSpecial4.contains("i")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            cannotAnalyze = true;
                        } else if (lineSpecial4.contains("j<=") && lineSpecial4.contains("i")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            cannotAnalyze = true;
                        } else if (lineSpecial4.contains("j>=") && lineSpecial4.contains("i")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            cannotAnalyze = true;
                        }
                        lineSpecial4 = reader.readLine();
                    }
                }
                lineSpecial4 = reader.readLine();
            }
            reader.reset();
            //Special Case 5: Dependent Linear
            String lineSpecial5 = reader.readLine();
            while (lineSpecial5 != null) {
                lineSpecial5 = lineSpecial5.replaceAll("\\s", "");
                if (lineSpecial5.contains("i*=")) {
                    while (lineSpecial5 != null) {
                        lineSpecial5 = lineSpecial5.replaceAll("\\s", "");
                        if (lineSpecial5.contains("j<i") && lineSpecial5.contains("j++")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            firstLoopN = true;
                        } else if (lineSpecial5.contains("j>i") && lineSpecial5.contains("j++")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            firstLoopN = true;
                        } else if (lineSpecial5.contains("j<=i") && lineSpecial5.contains("j++")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            firstLoopN = true;
                        } else if (lineSpecial5.contains("j>=i") && lineSpecial5.contains("j++")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            firstLoopN = true;
                        } else if (lineSpecial5.contains("j<i") && lineSpecial5.contains("j--")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            firstLoopN = true;
                        } else if (lineSpecial5.contains("j>i") && lineSpecial5.contains("j--")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            firstLoopN = true;
                        } else if (lineSpecial5.contains("j<=i") && lineSpecial5.contains("j--")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            firstLoopN = true;
                        } else if (lineSpecial5.contains("j>=i") && lineSpecial5.contains("j--")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            firstLoopN = true;
                        }
                        lineSpecial5 = reader.readLine();
                    }
                } else if (lineSpecial5.contains("i/=")) {
                    while (lineSpecial5 != null) {
                        lineSpecial5 = lineSpecial5.replaceAll("\\s", "");
                        if (lineSpecial5.contains("j<i") && lineSpecial5.contains("j++")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            firstLoopN = true;
                        } else if (lineSpecial5.contains("j>i") && lineSpecial5.contains("j++")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            firstLoopN = true;
                        } else if (lineSpecial5.contains("j<=i") && lineSpecial5.contains("j++")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            firstLoopN = true;
                        } else if (lineSpecial5.contains("j>=i") && lineSpecial5.contains("j++")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            firstLoopN = true;
                        } else if (lineSpecial5.contains("j<i") && lineSpecial5.contains("j--")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            firstLoopN = true;
                        } else if (lineSpecial5.contains("j>i") && lineSpecial5.contains("j--")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            firstLoopN = true;
                        } else if (lineSpecial5.contains("j<=i") && lineSpecial5.contains("j--")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            firstLoopN = true;
                        } else if (lineSpecial5.contains("j>=i") && lineSpecial5.contains("j--")) {
                            firstLoopAnalyzed = true;
                            secondLoopAnalyzed = true;
                            firstLoopN = true;
                        }
                        lineSpecial5 = reader.readLine();
                    }
                }
                lineSpecial5 = reader.readLine();
            }
            reader.reset();
            //analyzing normal cases
            System.out.println("Analyzing......");
            String line = reader.readLine();
            while (line != null) {
                line = line.replaceAll("\\s", "");
                if (line.contains("i++")) {
                    if (firstLoopAnalyzed == false) {
                        firstLoopN = true;
                        firstLoopAnalyzed = true;
                    }
                    while (line != null) {
                        line = line.replaceAll("\\s", "");
                        if (line.contains("j++")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j--")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j+=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j-=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j*=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopLogN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j/=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopLogN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        }
                        line = reader.readLine();
                    }
                } else if (line.contains("i--")) {
                    if (firstLoopAnalyzed == false) {
                        firstLoopN = true;
                        firstLoopAnalyzed = true;
                    }
                    while (line != null) {
                        line = line.replaceAll("\\s", "");
                        if (line.contains("j++")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j--")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j+=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j-=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j*=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopLogN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j/=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopLogN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        }
                        line = reader.readLine();
                    }
                } else if (line.contains("i+=")) {
                    if (firstLoopAnalyzed == false) {
                        firstLoopN = true;
                        firstLoopAnalyzed = true;
                    }
                    while (line != null) {
                        line = line.replaceAll("\\s", "");
                        if (line.contains("j++")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j--")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j+=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j-=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j*=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopLogN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j/=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopLogN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        }
                        line = reader.readLine();
                    }
                } else if (line.contains("i-=")) {
                    if (firstLoopAnalyzed == false) {
                        firstLoopN = true;
                        firstLoopAnalyzed = true;
                    }
                    while (line != null) {
                        line = line.replaceAll("\\s", "");
                        if (line.contains("j++")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j--")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j+=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j-=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j*=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopLogN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j/=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopLogN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        }
                        line = reader.readLine();
                    }
                } else if (line.contains("i*=")) {
                    if (firstLoopAnalyzed == false) {
                        firstLoopLogN = true;
                        firstLoopAnalyzed = true;
                    }
                    while (line != null) {
                        line = line.replaceAll("\\s", "");
                        if (line.contains("j++")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j--")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j+=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j-=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j*=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopLogN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j/=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopLogN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        }
                        line = reader.readLine();
                    }
                } else if (line.contains("i/=")) {
                    if (firstLoopAnalyzed == false) {
                        firstLoopLogN = true;
                        firstLoopAnalyzed = true;
                    }
                    while (line != null) {
                        line = line.replaceAll("\\s", "");
                        if (line.contains("j++")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j--")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j+=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j-=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j*=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopLogN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        } else if (line.contains("j/=")) {
                            if (secondLoopAnalyzed == false) {
                                noSecondLoop = false;
                                secondLoopLogN = true;
                                secondLoopAnalyzed = true;
                                break;
                            }
                        }
                        line = reader.readLine();
                    }
                }
                line = reader.readLine();
            }
            System.out.println("Creating time complexity.");
            String timeComplexity = timeComplexityMethod(varI, varJ, firstLoopConstant, firstLoopN, firstLoopLogN, firstLoopSqrtN, secondLoopConstant, secondLoopN, secondLoopLogN, secondLoopSqrtN, noSecondLoop);
            System.out.println("Placing BigO into BigO file.\n");
            if (cannotAnalyze == true) {
                printToOutputFile_CannotBeAnalyzed(f, directory);
            } else {
                printToOutputFile(f, timeComplexity, directory);
            }
        } catch (IOException e) {
            System.out.println("DEPTH ANALYSIS ERROR");
        }
    }

    public static void analyze(String directory) {
        System.out.println("Reading files...\n");
        File file = new File(directory);
        File[] files = file.listFiles();
        for (File f : files) {
            //Ignores BigO txt that already exist in the directory
            if (!f.getName().contains("BigO")) {
                depthAnalyze(f, directory);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanMain = new Scanner(System.in);
        System.out.println("Input File Directory:\nWARNING: The output files will be printed to this directory.\nEXAMPLE: C:\\Users\\James\\Desktop\\Towson\\COSC336\\forloops");
        String dir = scanMain.nextLine();
        System.out.println("Beginning analysis.\n");
        analyze(dir);
        System.out.println("Done.");
    }
}
