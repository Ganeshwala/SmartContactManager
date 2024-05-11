function toggleSidebar() {
    
    const sidebar =  document.getElementsByClassName("sidebar")[0];
    const content =  document.getElementsByClassName("content")[0];

    if(window.getComputedStyle(sidebar).display === "none"){
        sidebar.style.display = "block";
        content.style.marginLeft = "20%";
    }
    else{
        sidebar.style.display = "none";
        content.style.marginLeft = "0%";
    }
}

const uploadNewImg=()=>{
	
	let newPic = $("#profileImg").val();
	let userId = $("#uId").val();
	console.log("new Image====>"+newPic);
	if(newPic!=''){
		let url = `http://localhost:8080/EditProfile/${userId}/${newPic}`;
		
		fetch(url).then((data)=>{
			return response.json();
		}).then(result=>{
			window.reload();
		})
	}
}


const search=()=>{
	//console.log("searching progress")
	let keywords=$("#searchValue").val();
	console.log("searching progress"+keywords);
	if(keywords==''){
		$(".search-result").hide();
	}else{
		
		
		let url = `http://localhost:8080/search/${keywords}`;
		console.log(url)
		
		fetch(url).then((response)=>{
			return response.json();
		}).then((data)=>{
			//console.log(data)
			let diplayText = `<div class='list-group'>`;
			
			data.forEach((result)=>{
				diplayText += `<a href="/user/viewDeatis/${result.contactId}" class='list-group-item list-group-action'>${result.contactName}</a>`;
			})
			
			diplayText += `<div>`;
			
			$(".search-result").html(diplayText);
			$(".search-result").show();
		});
	}
}

$(function(){
  $('#upload').change(function(){
    var url = $(this).val();
    document.getElementById("form").submit();
  });

});