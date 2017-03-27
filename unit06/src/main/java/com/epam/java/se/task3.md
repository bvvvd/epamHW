| |correct|incorrect|commentaries|
|-----|:-----:|:-----:|-----|
|Doctor doctor1 = new Doctor();|x| |The type of the reference corresponds to the class|
|Doctor doctor2 = new MedicalStaff();| |x|Incompatible types, can be casted to Doctor|
|Doctor doctor3 = new HeadDoctor();|x| |Doctor IS HeadDoctor|
|Object object1 = new HeadDoctor();|x| |HeadDoctor IS Object|
|HeadDoctor doctor5 = new Object();| |x|Incompatible types, can be casted to HeadDoctor|
|Doctor doctor6  = new Nurse();| |x|Incompatible types, Doctor ISN'T Nurse|
|Nurse nurse = new Doctor();| |x|Incompatible types, Nurse ISN'T Doctor|
|Object object2 = new Nurse();|x| |Nurse IS Object|
|List<Doctor> list1= new ArrayList<Doctor>();|x| |List's Generic parameterization matches generic parameterization of ArrayList|
|List<MedicalStaff> list2 = new ArrayList<Doctor>();| |x|List's generic parameterization must matches parametrization of ArrayList, or must be of the form <? extends MedicalStaff>|
|List<Doctor> list3 = new ArrayList<MedicalStaff();|x| |List's generic parameterization must matches parametrization of ArrayList, or must be form of <? super Doctor>|
|List<Object> list4 = new ArrayList<Doctor>();| |x|List's generic parameterization must matches parameterization of ArrayList, or must be form of <? extends Object>|
|List<Object> list5 = new ArrayList<Object>();|x| |List's generic parameterization matches parameterization of ArrayList|