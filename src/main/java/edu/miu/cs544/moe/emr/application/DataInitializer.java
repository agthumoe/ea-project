package edu.miu.cs544.moe.emr.application;

import edu.miu.cs544.moe.emr.domain.address.Address;
import edu.miu.cs544.moe.emr.domain.category.Category;
import edu.miu.cs544.moe.emr.domain.category.CategoryRepository;
import edu.miu.cs544.moe.emr.domain.patient.Patient;
import edu.miu.cs544.moe.emr.domain.patient.PatientService;
import edu.miu.cs544.moe.emr.domain.shared.enums.BloodGroup;
import edu.miu.cs544.moe.emr.domain.shared.enums.Gender;
import edu.miu.cs544.moe.emr.domain.subcategory.SubCategory;
import edu.miu.cs544.moe.emr.domain.subcategory.SubCategoryRepository;
import edu.miu.cs544.moe.emr.domain.user.UserService;
import edu.miu.cs544.moe.emr.domain.user.dto.CreateUser;
import edu.miu.cs544.moe.emr.domain.vitals.Vital;
import edu.miu.cs544.moe.emr.domain.vitals.VitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
@Profile("data")
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private VitalRepository vitalRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Step 1: Create 10 Users
        userService.create(new CreateUser("Alice Johnson", "alicej", "Passw0rd!"));
        userService.create(new CreateUser("Bob Smith", "bobsmith", "Secure123"));
        userService.create(new CreateUser("Charlie Davis", "charlied", "MyP@ssword1"));
        userService.create(new CreateUser("Diana Evans", "dianae", "D1anaEvans!"));
        userService.create(new CreateUser("Ethan Foster", "ethanf", "Eth@nFoster"));
        userService.create(new CreateUser("Fiona Green", "fionag", "F!onaGreen"));
        userService.create(new CreateUser("George Harris", "georgeh", "G3orgeH"));
        userService.create(new CreateUser("Hannah Irwin", "hannahi", "H@nnahI"));
        userService.create(new CreateUser("Ian Jackson", "ianj", "IanJ@ckson"));
        userService.create(new CreateUser("Julia King", "juliak", "JuliaK1ng"));

        // Step 2: Create 10 Categories with SubCategories

        // Category 1: In-Patient
        Category inPatient = new Category("In-Patient", "Patients admitted to the hospital");
        categoryRepository.save(inPatient);

        SubCategory generalSurgery = new SubCategory("General Surgery", "General surgical procedures", inPatient);
        subCategoryRepository.save(generalSurgery);

        // Category 2: Out-Patient
        Category outPatient = new Category("Out-Patient", "Patients visiting for consultations");
        categoryRepository.save(outPatient);

        SubCategory dermatology = new SubCategory("Dermatology", "Skin related treatments", outPatient);
        SubCategory cardiology = new SubCategory("Cardiology", "Heart related treatments", outPatient);
        subCategoryRepository.save(dermatology);
        subCategoryRepository.save(cardiology);

        // Category 3: Emergency
        Category emergency = new Category("Emergency", "Patients requiring immediate attention");
        categoryRepository.save(emergency);

        SubCategory trauma = new SubCategory("Trauma", "Traumatic injuries", emergency);
        SubCategory neurology = new SubCategory("Neurology", "Nervous system treatments", emergency);
        SubCategory orthopedics = new SubCategory("Orthopedics", "Bone and muscle treatments", emergency);
        subCategoryRepository.save(trauma);
        subCategoryRepository.save(neurology);
        subCategoryRepository.save(orthopedics);

        // Category 4: Pediatrics
        Category pediatrics = new Category("Pediatrics", "Child patients");
        categoryRepository.save(pediatrics);

        SubCategory neonatology = new SubCategory("Neonatology", "Care for newborns", pediatrics);
        SubCategory adolescentMedicine = new SubCategory("Adolescent Medicine", "Care for teenagers", pediatrics);
        subCategoryRepository.save(neonatology);
        subCategoryRepository.save(adolescentMedicine);

        // Category 5: Maternity
        Category maternity = new Category("Maternity", "Pregnant patients");
        categoryRepository.save(maternity);

        SubCategory obstetrics = new SubCategory("Obstetrics", "Pregnancy and childbirth", maternity);
        SubCategory gynecology = new SubCategory("Gynecology", "Women's reproductive health", maternity);
        SubCategory neonatologyMaternity = new SubCategory("Neonatology", "Care for newborns", maternity);
        subCategoryRepository.save(obstetrics);
        subCategoryRepository.save(gynecology);
        subCategoryRepository.save(neonatologyMaternity);

        // Category 6: Oncology
        Category oncology = new Category("Oncology", "Cancer patients");
        categoryRepository.save(oncology);

        SubCategory radiationTherapy = new SubCategory("Radiation Therapy", "Cancer radiation treatments", oncology);
        SubCategory chemotherapy = new SubCategory("Chemotherapy", "Cancer chemotherapy treatments", oncology);
        SubCategory surgicalOncology = new SubCategory("Surgical Oncology", "Surgical cancer treatments", oncology);
        subCategoryRepository.save(radiationTherapy);
        subCategoryRepository.save(chemotherapy);
        subCategoryRepository.save(surgicalOncology);

        // Category 7: Psychiatry
        Category psychiatry = new Category("Psychiatry", "Mental health patients");
        categoryRepository.save(psychiatry);

        SubCategory clinicalPsychology = new SubCategory("Clinical Psychology", "Psychological assessments and therapy", psychiatry);
        SubCategory counseling = new SubCategory("Counseling", "Therapeutic counseling services", psychiatry);
        subCategoryRepository.save(clinicalPsychology);
        subCategoryRepository.save(counseling);

        // Category 8: Rehabilitation
        Category rehabilitation = new Category("Rehabilitation", "Patients undergoing rehab");
        categoryRepository.save(rehabilitation);

        SubCategory physicalTherapy = new SubCategory("Physical Therapy", "Physical rehabilitation", rehabilitation);
        SubCategory occupationalTherapy = new SubCategory("Occupational Therapy", "Occupational rehabilitation", rehabilitation);
        SubCategory speechTherapy = new SubCategory("Speech Therapy", "Speech and language therapy", rehabilitation);
        SubCategory recreationalTherapy = new SubCategory("Recreational Therapy", "Recreational activities for rehab", rehabilitation);
        subCategoryRepository.save(physicalTherapy);
        subCategoryRepository.save(occupationalTherapy);
        subCategoryRepository.save(speechTherapy);
        subCategoryRepository.save(recreationalTherapy);

        // Category 9: Geriatrics
        Category geriatrics = new Category("Geriatrics", "Elderly patients");
        categoryRepository.save(geriatrics);

        SubCategory dementiaCare = new SubCategory("Dementia Care", "Care for dementia patients", geriatrics);
        SubCategory mobilityAssistance = new SubCategory("Mobility Assistance", "Assistance with movement", geriatrics);
        subCategoryRepository.save(dementiaCare);
        subCategoryRepository.save(mobilityAssistance);

        // Category 10: Radiology
        Category radiology = new Category("Radiology", "Imaging services");
        categoryRepository.save(radiology);

        SubCategory xRay = new SubCategory("X-Ray", "X-Ray imaging", radiology);
        SubCategory mri = new SubCategory("MRI", "Magnetic Resonance Imaging", radiology);
        SubCategory ctScan = new SubCategory("CT Scan", "Computed Tomography Scans", radiology);
        subCategoryRepository.save(xRay);
        subCategoryRepository.save(mri);
        subCategoryRepository.save(ctScan);

        // Step 3: Create 10 Patients with Assigned Categories and Vitals

        // Patient 1
        Patient patient1 = patientService.create(new Patient(
                "Michael Brown",
                Gender.MALE,
                "Laura Brown",
                LocalDate.of(1985, 4, 12),
                BloodGroup.A_POSITIVE,
                new Address("123 Maple Street", "Springfield", "IL", "62704")
        ));
        // Assign Category: In-Patient
        inPatient.addPatient(patient1);
        categoryRepository.save(inPatient);
        // Assign Vitals
        vitalRepository.save(new Vital(patient1, 98, 72, 120, 80, 16, 98, 90, 180, 22));

        // Patient 2
        Patient patient2 = patientService.create(new Patient(
                "Sarah Wilson",
                Gender.FEMALE,
                "Thomas Wilson",
                LocalDate.of(1992, 7, 23),
                BloodGroup.B_NEGATIVE,
                new Address("456 Oak Avenue", "Greenville", "TX", "75402")
        ));
        // Assign Category: Out-Patient
        outPatient.addPatient(patient2);
        categoryRepository.save(outPatient);
        // Assign Vitals
        vitalRepository.save(new Vital(patient2, 99, 75, 118, 78, 18, 97, 85, 175, 21));

        // Patient 3
        Patient patient3 = patientService.create(new Patient(
                "James Taylor",
                Gender.MALE,
                "Patricia Taylor",
                LocalDate.of(1978, 11, 5),
                BloodGroup.O_POSITIVE,
                new Address("789 Pine Road", "Madison", "WI", "53703")
        ));
        // Assign Category: Emergency
        emergency.addPatient(patient3);
        categoryRepository.save(emergency);
        // Assign Vitals
        vitalRepository.save(new Vital(patient3, 100, 80, 122, 82, 17, 99, 95, 190, 24));

        // Patient 4
        Patient patient4 = patientService.create(new Patient(
                "Laura Martinez",
                Gender.FEMALE,
                "Carlos Martinez",
                LocalDate.of(1988, 2, 28),
                BloodGroup.AB_NEGATIVE,
                new Address("321 Birch Boulevard", "Fairview", "CA", "94541")
        ));
        // Assign Category: Pediatrics
        pediatrics.addPatient(patient4);
        categoryRepository.save(pediatrics);
        // Assign Vitals
        vitalRepository.save(new Vital(patient4, 97, 68, 115, 75, 15, 96, 88, 170, 20));

        // Patient 5
        Patient patient5 = patientService.create(new Patient(
                "Daniel Anderson",
                Gender.MALE,
                "Susan Anderson",
                LocalDate.of(1995, 6, 14),
                BloodGroup.O_NEGATIVE,
                new Address("654 Cedar Lane", "Clinton", "NY", "13323")
        ));
        // Assign Category: Oncology
        oncology.addPatient(patient5);
        categoryRepository.save(oncology);
        // Assign Vitals
        vitalRepository.save(new Vital(patient5, 101, 85, 130, 85, 19, 100, 100, 200, 25));

        // Patient 6
        Patient patient6 = patientService.create(new Patient(
                "Emma Thomas",
                Gender.FEMALE,
                "Robert Thomas",
                LocalDate.of(2000, 9, 30),
                BloodGroup.B_POSITIVE,
                new Address("987 Walnut Drive", "Franklin", "OH", "45005")
        ));
        // Assign Category: Rehabilitation
        rehabilitation.addPatient(patient6);
        categoryRepository.save(rehabilitation);
        // Assign Vitals
        vitalRepository.save(new Vital(patient6, 98, 70, 119, 79, 16, 97, 92, 185, 23));

        // Patient 7
        Patient patient7 = patientService.create(new Patient(
                "Matthew Lee",
                Gender.MALE,
                "Linda Lee",
                LocalDate.of(1975, 3, 18),
                BloodGroup.A_POSITIVE,
                new Address("135 Cherry Court", "Georgetown", "KY", "40324")
        ));
        // Assign Category: Maternity
        maternity.addPatient(patient7);
        categoryRepository.save(maternity);
        // Assign Vitals
        vitalRepository.save(new Vital(patient7, 99, 73, 121, 81, 17, 98, 89, 178, 21));

        // Patient 8
        Patient patient8 = patientService.create(new Patient(
                "Olivia Harris",
                Gender.FEMALE,
                "Mark Harris",
                LocalDate.of(1983, 12, 9),
                BloodGroup.AB_POSITIVE,
                new Address("246 Aspen Way", "Bristol", "VA", "24201")
        ));
        // Assign Category: Radiology
        radiology.addPatient(patient8);
        categoryRepository.save(radiology);
        // Assign Vitals
        vitalRepository.save(new Vital(patient8, 100, 77, 125, 83, 18, 99, 93, 195, 22));

        // Patient 9
        Patient patient9 = patientService.create(new Patient(
                "Andrew Clark",
                Gender.MALE,
                "Barbara Clark",
                LocalDate.of(1965, 5, 22),
                BloodGroup.O_NEGATIVE,
                new Address("579 Elm Street", "Milton", "FL", "32756")
        ));
        // Assign Category: Geriatrics
        geriatrics.addPatient(patient9);
        categoryRepository.save(geriatrics);
        // Assign Vitals
        vitalRepository.save(new Vital(patient9, 97, 69, 117, 76, 16, 96, 87, 172, 20));

        // Patient 10
        Patient patient10 = patientService.create(new Patient(
                "Sophia Lewis",
                Gender.FEMALE,
                "Kevin Lewis",
                LocalDate.of(1998, 10, 5),
                BloodGroup.B_NEGATIVE,
                new Address("864 Poplar Street", "Auburn", "AL", "36830")
        ));
        // Assign Category: Psychiatry
        psychiatry.addPatient(patient10);
        categoryRepository.save(psychiatry);
        // Assign Vitals
        vitalRepository.save(new Vital(patient10, 98, 74, 123, 84, 19, 98, 94, 188, 23));

        // All Categories and Patients have been saved appropriately
    }
}
