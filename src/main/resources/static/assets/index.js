// Funções relacionadas ao disclaimer e modal
function showModal() {
    const modal = document.querySelector("#termsModal");
    modal.style.display = 'block';
}

function hideModal() {
    const modal = document.querySelector("#termsModal");
    modal.style.display = 'none';
}

function closeDisclaimer() {
    document.querySelector(".disclaimer").style.display = 'none';
    document.cookie = "disclaimer_closed=true; path=/; max-age=86400";  // Cookie válido por 1 dia
}
// Fetch the subpaths and populate the sidebar
$(document).ready(function() {
    $.get('/api/pages/subitems?path=' + window.location.pathname, function(data) {
        if (data.length === 0) {
            $('#sidebar').hide();
            $('#editor-container').removeClass('col-md-10').addClass('col-md-12');
        } else {
            data.forEach(function(path, index) {
                if (index === 0 && (path === '/' || window.location.pathname.startsWith(path))) {
                    $('#subpaths').append('<a href="' + path + '" class="d-block mb-1">.. (Up One Level)</a>');
                } else {
                    $('#subpaths').append('<a href="' + path + '" class="d-block mb-1">' + path + '</a>');
                }
            });
        }
    });
});

function checkDisclaimerCookie() {
    if (document.cookie.split(';').some((item) => item.trim().startsWith('disclaimer_closed='))) {
        document.querySelector(".disclaimer").style.display = 'none';
    }
}

checkDisclaimerCookie(); // Executando a verificação no carregamento da página

// This function will run when all the async scripts have been loaded
window.onload = function () {
    document.title = 'Maybepad' + window.location.pathname;
    window.dataLayer = window.dataLayer || [];
    function gtag() {
        dataLayer.push(arguments);
    }
    gtag('js', new Date());
    gtag('config', 'G-FBBVJ554L4');

    ClassicEditor
        .create(document.querySelector('#editor'), {
            fontColor: {
                colors: [
                    {
                        color: 'hsl(0, 0%, 0%)',
                        label: 'Black'
                    }
                ]
            }
        })
        .then(editor => {
            var path = window.location.pathname;
            console.log("loaded editor in path: ", path);

            var currentContent = "";
            var saveTimeout = null;
            var saveDelay = 2000; // Save delay in milliseconds

            function saveContent() {
                var content = editor.getData();
                if(content !== currentContent) {
                    currentContent = content;
                    console.log("requesting save in path: ", path);
                    // Alterado para /api/pages
                    $.ajax({
                        url: '/api/pages/save_page?path=' + path,
                        type: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify({
                            path: path,
                            content: content
                        }),
                        success: function() {
                            console.log('Saved');
                        }
                    });
                }
            }

            function loadContent() {
                console.log("requesting load in path: ", path);
                // Alterado para /api/pages
                $.ajax({
                    url: '/api/pages/get_page?path=' + path,
                    type: 'GET',
                    success: function(data) {
                        console.log("get ok");
                        console.log(data);
                        currentContent = data.content;
                        editor.setData(data.content);
                    }
                });
            }

            editor.model.document.on('change:data', () => {
                clearTimeout(saveTimeout);
                saveTimeout = setTimeout(saveContent, saveDelay);
            });

            loadContent(); // Load content after editor is ready
        })
        .catch(error => {
            console.error(error);
        });
};

// Verificar se o cookie de aceitação do disclaimer existe e ocultar o disclaimer se necessário
$(document).ready(function() {
    if (document.cookie.split(';').some((item) => item.trim().startsWith('disclaimerAccepted='))) {
        $(".disclaimer").hide();
    }
});

// Definir o cookie quando o usuário aceita o disclaimer
$(".close").click(function() {
    $(".disclaimer").hide(); // Esconder o disclaimer
});

$(".accept-eula").click(function() {
    document.cookie = "disclaimerAccepted=true; max-age=31536000; path=/"; // Define o cookie para 1 ano
});
