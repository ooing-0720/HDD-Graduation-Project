function deptChange(e){
    var stud = ["- - - - - - - - - - - - - - "];
    var engin = ["- - - - - - - - - - - - - - ","건설환경공학과","전자전기공학부","컴퓨터공학과","산업데이터공학과","신소재공학전공","화학공학전공","기계시스템디자인공학과"];
    var archi = ["- - - - - - - - - - - - - - ","건축학부","도시공학과"];
    var manag = ["경영학부"];
    var liberal = ["- - - - - - - - - - - - - - -","영어영문학과","독어독문학과","불어불문학과","국어국문학과"];
    var law = ["법학부"];
    var edu = ["- - - - - - - - - - - - - - - ","수학교육과","국어교욱과","영어교육과","역사교육과","교육학과"];
    var art = ["- - - - - - - - - - - - - - - ","동양화과","회화과","판화과","조소과","목조형가구학과","예술학과","금속조형디자인과","도예유리과","섬유미술패션디자인과"];
    var eco = ["경제학전공"];
    var perform = ["- - - - - - - - - - - - - - - ","뮤지컬전공","실용음악전공"];
    var dema = ["- - - - - - - - - - - - - - - "];
    var free = ["- - - - - - - - - - - - - - - "];
    var target = document.getElementById("dept2");

    if(e.value == "학생공지") var a = stud;
    else if(e.value == "공과대학") var a = engin;
    else if(e.value == "건축도시대학") var a = archi;
    else if(e.value == "경영대학") var a = manag;
    else if(e.value == "문과대학") var a = liberal;
    else if(e.value == "법과대학") var a = law;
    else if(e.value == "사범대학") var a = edu;
    else if(e.value == "미술대학") var a = art;
    else if(e.value == "경제학부") var a = eco;
    else if(e.value == "공연예술학부") var a = perform;
    else if(e.value == "디자인경영융합") var a = dema;
    else if(e.value == "자율전공") var a = free;
    target.options.length = 0;

    for(x in a) {
        var opt = document.createElement("option");
        opt.value = a[x];
        opt.innerHTML = a[x];
        target.appendChild(opt);
    }
}
