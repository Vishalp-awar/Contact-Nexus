console.log("contactmodal.js");

const viewContactModal = document.getElementById('view_contact_modal');
const baseUrl="http://localhost:8081"
 if (!viewContactModal) {
      console.error("view_contact_modal element not found!");
    }

// options with default values
const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};
const instanceOptions = {
  id: 'view_contact_modal',
  override: true
};
const contactmodal = new Modal(viewContactModal,options,instanceOptions)

function openContactModal(){
    contactmodal.show();
}
function closeContactModal(){
    contactmodal.hide();
}

//async function loadContactData(id){
//    console.log(id);
//
//    fetch(`http://localhost:8081/api/contacts/${id}`)
//    .then(async(response) => {
//        const data= await response.json()
//        console.log(data);
//        return data;
//    }).catch((error) => {
//        console.log(error);
//    })
//    }
//
//}

async function loadContactData(id) {
  //function call to load data
//  console.log(id);
  try {
    const data = await (await fetch(`${baseUrl}/api/contacts/${id}`)).json();
    console.log(data);
    document.querySelector("#contact_name").innerHTML = data.name;
        document.querySelector("#contact_email").innerHTML = data.email;
        document.querySelector("#contact_image").src = data.picture;
        document.querySelector("#contact_address").innerHTML = data.address;
        document.querySelector("#contact_phone").innerHTML = data.phoneNumber;
        document.querySelector("#contact_about").innerHTML = data.description;
        const contactFavorite = document.querySelector("#contact_favorite");
        if (data.favorite) {
          contactFavorite.innerHTML =
            "<i class='fas fa-star text-yellow-400'></i><i class='fas fa-star text-yellow-400'></i><i class='fas fa-star text-yellow-400'></i><i class='fas fa-star text-yellow-400'></i><i class='fas fa-star text-yellow-400'></i>";
        } else {
          contactFavorite.innerHTML = "Not Favorite Contact";
        }

        document.querySelector("#contact_website").href = data.websiteLink;
        document.querySelector("#contact_website").innerHTML = data.websiteLink;
        document.querySelector("#contact_linkedIn").href = data.linkedInLink;
        document.querySelector("#contact_linkedIn").innerHTML = data.linkedInLink;
        openContactModal();

  } catch (error) {
    console.log("Error: ", error);
  }
}

//delete contact

//async function deleteContact(id){
//
//    Swal.fire({
//      title: "Do you want to Delete the Contact?",
//      icon:"warning",
//      showCancelButton: true,
//      confirmButtonText: "Delete",
//      denyButtonText: `Don't Delete`
//    }).then((result) => {
//      /* Read more about isConfirmed, isDenied below */
//      if (result.isConfirmed) {
//        Swal.fire("Saved!", "", "success");
//
//        const url = `http://${baseUrl}/user/contacts/delete/`+id;
//        window.location.replace(url)
//
//      } else if (result.isDenied) {
//        Swal.fire("Changes are not saved", "", "info");
//      }
//    });
//
//}
async function deleteContact(id) {
  Swal.fire({
    title: "Do you want to Delete the Contact?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "Delete",
    denyButtonText: `Don't Delete`,
    customClass: {
      confirmButton: 'btn-confirm', // Custom class for the confirm button
      cancelButton: 'btn-cancel',  // Custom class for the cancel button
    },
    buttonsStyling: false, // Disable default button styling to apply your custom styles
  }).then((result) => {
    if (result.isConfirmed) {
      Swal.fire("Deleted Successfull    y!", "", "success");

      // Ensure no duplicate `http://`
      const url = `${baseUrl}/user/contacts/delete/${id}`;
      window.location.replace(url); // Redirects to the corrected URL
    } else if (result.isDenied) {
      Swal.fire("Changes are not saved", "", "info");
    }
  });
}
//update form view

