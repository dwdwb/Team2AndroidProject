package com.example.myapplication.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentSearchBinding;
import com.example.myapplication.datastore.AppKeyValueStore;

public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";
    private FragmentSearchBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater);

        //NavController 얻기
        navController = NavHostFragment.findNavController(this);

        initBtnList();
        initBtnBack();

        initRecentSearch();
        initBtnDelete();

        // EditText를 찾아서 포커스 설정
        //binding.search.requestFocus();
        //setShowKeyBoard();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        initRecentSearch();
        initBtnDelete();

        // EditText를 찾아서 포커스 설정
        binding.search.setText("");
        binding.search.requestFocus();
        setShowKeyBoard();
    }

    private void initBtnList() {
        binding.btnList.setOnClickListener(v -> {
            String keyword = binding.search.getText().toString();
            Bundle args = new Bundle();
            args.putSerializable("keyword", keyword);

            saveKeyword(keyword);

            navController.navigate(R.id.action_search_to_list, args);
        });

        binding.search.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String keyword = binding.search.getText().toString();
                Bundle args = new Bundle();
                args.putSerializable("keyword", keyword);

                saveKeyword(keyword);

                navController.navigate(R.id.action_search_to_list, args);

                return true;
            }
            return false;
        });
    }

    public void saveKeyword(String keyword) {
        boolean duplicated = false;
        int index = -1;
        for (int i=1; i<=10; ++i) {
            if (AppKeyValueStore.getValue(getContext(), "searchHistory"+(i)) == null) {
                break;
            } else {
                if (AppKeyValueStore.getValue(getContext(), "searchHistory"+(i)).equals(keyword)) {
                    index = i;
                    duplicated = true;
                }
            }
        }

        // 중복이 아님
        if (!duplicated) {
            for (int i=10; i>1; --i) {
                AppKeyValueStore.put(getContext(), "searchHistory"+i, AppKeyValueStore.getValue(getContext(), "searchHistory"+(i-1)));
            }
            AppKeyValueStore.put(getContext(), "searchHistory1", keyword);
        } else {
            if (index > 1) {
                for (int i=index; i>=2; --i) {
                    AppKeyValueStore.put(getContext(), "searchHistory"+i, AppKeyValueStore.getValue(getContext(), "searchHistory"+(i-1)));
                }
                AppKeyValueStore.put(getContext(), "searchHistory1", keyword);
            }
        }


    }

    private void initBtnBack() {
        binding.btnBack.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

    private void setShowKeyBoard() {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(binding.search, InputMethodManager.SHOW_IMPLICIT);
    }

    private void initRecentSearch() {
        if (AppKeyValueStore.getValue(getContext(), "searchHistory1") == null) {
            binding.textRecentSearch.setVisibility(View.GONE);
            binding.btnDeleteAll.setVisibility(View.GONE);
        }

        if (AppKeyValueStore.getValue(getContext(), "searchHistory1") != null) {
            binding.textRecentSearch.setVisibility(View.VISIBLE);
            binding.btnDeleteAll.setVisibility(View.VISIBLE);

            binding.searchHistoryLayout1.setVisibility(View.VISIBLE);
            binding.searchHistoryKeyword1.setText(AppKeyValueStore.getValue(getContext(), "searchHistory1"));
            binding.searchHistoryKeyword1.setOnClickListener(v -> {
                Bundle args = new Bundle();
                String keyword = AppKeyValueStore.getValue(getContext(), "searchHistory1");
                args.putSerializable("keyword", keyword);
                saveKeyword(keyword);
                navController.navigate(R.id.action_search_to_list, args);
            });
        } else {
            binding.searchHistoryLayout1.setVisibility(View.INVISIBLE);
        }

        if (AppKeyValueStore.getValue(getContext(), "searchHistory2") != null) {
            binding.searchHistoryLayout2.setVisibility(View.VISIBLE);
            binding.searchHistoryKeyword2.setText(AppKeyValueStore.getValue(getContext(), "searchHistory2"));
            binding.searchHistoryKeyword2.setOnClickListener(v -> {
                Bundle args = new Bundle();
                String keyword = AppKeyValueStore.getValue(getContext(), "searchHistory2");
                args.putSerializable("keyword", keyword);
                saveKeyword(keyword);
                navController.navigate(R.id.action_search_to_list, args);
            });
        } else {
            binding.searchHistoryLayout2.setVisibility(View.INVISIBLE);
        }

        if (AppKeyValueStore.getValue(getContext(), "searchHistory3") != null) {
            binding.searchHistoryLayout3.setVisibility(View.VISIBLE);
            binding.searchHistoryKeyword3.setText(AppKeyValueStore.getValue(getContext(), "searchHistory3"));
            binding.searchHistoryKeyword3.setOnClickListener(v -> {
                Bundle args = new Bundle();
                String keyword = AppKeyValueStore.getValue(getContext(), "searchHistory3");
                args.putSerializable("keyword", keyword);
                saveKeyword(keyword);
                navController.navigate(R.id.action_search_to_list, args);
            });
        } else {
            binding.searchHistoryLayout3.setVisibility(View.INVISIBLE);
        }

        if (AppKeyValueStore.getValue(getContext(), "searchHistory4") != null) {
            binding.searchHistoryLayout4.setVisibility(View.VISIBLE);
            binding.searchHistoryKeyword4.setText(AppKeyValueStore.getValue(getContext(), "searchHistory4"));
            binding.searchHistoryKeyword4.setOnClickListener(v -> {
                Bundle args = new Bundle();
                String keyword = AppKeyValueStore.getValue(getContext(), "searchHistory4");
                args.putSerializable("keyword", keyword);
                saveKeyword(keyword);
                navController.navigate(R.id.action_search_to_list, args);
            });
        } else {
            binding.searchHistoryLayout4.setVisibility(View.INVISIBLE);
        }

        if (AppKeyValueStore.getValue(getContext(), "searchHistory5") != null) {
            binding.searchHistoryLayout5.setVisibility(View.VISIBLE);
            binding.searchHistoryKeyword5.setText(AppKeyValueStore.getValue(getContext(), "searchHistory5"));
            binding.searchHistoryKeyword5.setOnClickListener(v -> {
                Bundle args = new Bundle();
                String keyword = AppKeyValueStore.getValue(getContext(), "searchHistory5");
                args.putSerializable("keyword", keyword);
                saveKeyword(keyword);
                navController.navigate(R.id.action_search_to_list, args);
            });
        } else {
            binding.searchHistoryLayout5.setVisibility(View.INVISIBLE);
        }

        if (AppKeyValueStore.getValue(getContext(), "searchHistory6") != null) {
            binding.searchHistoryLayout6.setVisibility(View.VISIBLE);
            binding.searchHistoryKeyword6.setText(AppKeyValueStore.getValue(getContext(), "searchHistory6"));
            binding.searchHistoryKeyword6.setOnClickListener(v -> {
                Bundle args = new Bundle();
                String keyword = AppKeyValueStore.getValue(getContext(), "searchHistory6");
                args.putSerializable("keyword", keyword);
                saveKeyword(keyword);
                navController.navigate(R.id.action_search_to_list, args);
            });
        } else {
            binding.searchHistoryLayout6.setVisibility(View.INVISIBLE);
        }

        if (AppKeyValueStore.getValue(getContext(), "searchHistory7") != null) {
            binding.searchHistoryLayout7.setVisibility(View.VISIBLE);
            binding.searchHistoryKeyword7.setText(AppKeyValueStore.getValue(getContext(), "searchHistory7"));
            binding.searchHistoryKeyword7.setOnClickListener(v -> {
                Bundle args = new Bundle();
                String keyword = AppKeyValueStore.getValue(getContext(), "searchHistory7");
                args.putSerializable("keyword", keyword);
                saveKeyword(keyword);
                navController.navigate(R.id.action_search_to_list, args);
            });
        } else {
            binding.searchHistoryLayout7.setVisibility(View.INVISIBLE);
        }

        if (AppKeyValueStore.getValue(getContext(), "searchHistory8") != null) {
            binding.searchHistoryLayout8.setVisibility(View.VISIBLE);
            binding.searchHistoryKeyword8.setText(AppKeyValueStore.getValue(getContext(), "searchHistory8"));
            binding.searchHistoryKeyword8.setOnClickListener(v -> {
                Bundle args = new Bundle();
                String keyword = AppKeyValueStore.getValue(getContext(), "searchHistory8");
                args.putSerializable("keyword", keyword);
                saveKeyword(keyword);
                navController.navigate(R.id.action_search_to_list, args);
            });
        } else {
            binding.searchHistoryLayout8.setVisibility(View.INVISIBLE);
        }

        if (AppKeyValueStore.getValue(getContext(), "searchHistory9") != null) {
            binding.searchHistoryLayout9.setVisibility(View.VISIBLE);
            binding.searchHistoryKeyword9.setText(AppKeyValueStore.getValue(getContext(), "searchHistory9"));
            binding.searchHistoryKeyword9.setOnClickListener(v -> {
                Bundle args = new Bundle();
                String keyword = AppKeyValueStore.getValue(getContext(), "searchHistory9");
                args.putSerializable("keyword", keyword);
                saveKeyword(keyword);
                navController.navigate(R.id.action_search_to_list, args);
            });
        } else {
            binding.searchHistoryLayout9.setVisibility(View.INVISIBLE);
        }

        if (AppKeyValueStore.getValue(getContext(), "searchHistory10") != null) {
            binding.searchHistoryLayout10.setVisibility(View.VISIBLE);
            binding.searchHistoryKeyword10.setText(AppKeyValueStore.getValue(getContext(), "searchHistory10"));
            binding.searchHistoryKeyword10.setOnClickListener(v -> {
                Bundle args = new Bundle();
                String keyword = AppKeyValueStore.getValue(getContext(), "searchHistory10");
                args.putSerializable("keyword", keyword);
                saveKeyword(keyword);
                navController.navigate(R.id.action_search_to_list, args);
            });
        } else {
            binding.searchHistoryLayout10.setVisibility(View.INVISIBLE);
        }
        /*
        for (int i=1; i<=10; ++i) {
            if (AppKeyValueStore.getValue(getContext(), "searchHistory"+i) != null) {
                Log.i(TAG, AppKeyValueStore.getValue(getContext(), "searchHistory"+i));
            } else {
                break;
            }

        }
        */

    }

    private void initBtnDelete() {
        binding.btnDeleteAll.setOnClickListener(v -> {
            for (int i=1; i<=10; ++i) {
                AppKeyValueStore.remove(getContext(), "searchHistory" + i);
            }
            initRecentSearch();
        });

        binding.searchHistoryDeleteBtn1.setOnClickListener(v -> {
            // 1번이 마지막 번호라면 1번 검색어 삭제
            if (AppKeyValueStore.getValue(getContext(), "searchHistory2") == null) {
                AppKeyValueStore.remove(getContext(), "searchHistory1");
            }
            // 1번 이후로 검색어가 남아있다면 앞으로 한칸씩 땡기고 마지막 번호는 삭제
            else {
                for (int i=1; i<=9; ++i) {
                    if (AppKeyValueStore.getValue(getContext(), "searchHistory"+(i+1)) != null) {
                        AppKeyValueStore.put(getContext(), "searchHistory"+i,
                                AppKeyValueStore.getValue(getContext(), "searchHistory"+(i+1)));
                        AppKeyValueStore.remove(getContext(), "searchHistory"+(i+1));
                    }
                }
            }
            // 최근 검색어 리스트 갱신
            initRecentSearch();
        });

        binding.searchHistoryDeleteBtn2.setOnClickListener(v -> {
            // 2번이 마지막 번호라면 2번 검색어 삭제
            if (AppKeyValueStore.getValue(getContext(), "searchHistory3") == null) {
                AppKeyValueStore.remove(getContext(), "searchHistory2");
            }
            // 2번 이후로 검색어가 남아있다면 앞으로 한칸씩 땡기고 마지막 번호는 삭제
            else {
                for (int i=2; i<=9; ++i) {
                    if (AppKeyValueStore.getValue(getContext(), "searchHistory"+(i+1)) != null) {
                        AppKeyValueStore.put(getContext(), "searchHistory"+i,
                                AppKeyValueStore.getValue(getContext(), "searchHistory"+(i+1)));
                        AppKeyValueStore.remove(getContext(), "searchHistory"+(i+1));
                    }
                }
            }
            // 최근 검색어 리스트 갱신
            initRecentSearch();
        });

        binding.searchHistoryDeleteBtn3.setOnClickListener(v -> {
            // 3번이 마지막 번호라면 3번 검색어 삭제
            if (AppKeyValueStore.getValue(getContext(), "searchHistory4") == null) {
                AppKeyValueStore.remove(getContext(), "searchHistory3");
            }
            // 1번 이후로 검색어가 남아있다면 앞으로 한칸씩 땡기고 마지막 번호는 삭제
            else {
                for (int i=3; i<=9; ++i) {
                    if (AppKeyValueStore.getValue(getContext(), "searchHistory"+(i+1)) != null) {
                        AppKeyValueStore.put(getContext(), "searchHistory"+i,
                                AppKeyValueStore.getValue(getContext(), "searchHistory"+(i+1)));
                        AppKeyValueStore.remove(getContext(), "searchHistory"+(i+1));
                    }
                }
            }
            // 최근 검색어 리스트 갱신
            initRecentSearch();
        });

        binding.searchHistoryDeleteBtn4.setOnClickListener(v -> {
            // 4번이 마지막 번호라면 4번 검색어 삭제
            if (AppKeyValueStore.getValue(getContext(), "searchHistory5") == null) {
                AppKeyValueStore.remove(getContext(), "searchHistory4");
            }
            // 4번 이후로 검색어가 남아있다면 앞으로 한칸씩 땡기고 마지막 번호는 삭제
            else {
                for (int i=4; i<=9; ++i) {
                    if (AppKeyValueStore.getValue(getContext(), "searchHistory"+(i+1)) != null) {
                        AppKeyValueStore.put(getContext(), "searchHistory"+i,
                                AppKeyValueStore.getValue(getContext(), "searchHistory"+(i+1)));
                        AppKeyValueStore.remove(getContext(), "searchHistory"+(i+1));
                    }
                }
            }
            // 최근 검색어 리스트 갱신
            initRecentSearch();
        });

        binding.searchHistoryDeleteBtn5.setOnClickListener(v -> {
            // 5번이 마지막 번호라면 5번 검색어 삭제
            if (AppKeyValueStore.getValue(getContext(), "searchHistory6") == null) {
                AppKeyValueStore.remove(getContext(), "searchHistory5");
            }
            // 5번 이후로 검색어가 남아있다면 앞으로 한칸씩 땡기고 마지막 번호는 삭제
            else {
                for (int i=5; i<=9; ++i) {
                    if (AppKeyValueStore.getValue(getContext(), "searchHistory"+(i+1)) != null) {
                        AppKeyValueStore.put(getContext(), "searchHistory"+i,
                                AppKeyValueStore.getValue(getContext(), "searchHistory"+(i+1)));
                        AppKeyValueStore.remove(getContext(), "searchHistory"+(i+1));
                    }
                }
            }
            // 최근 검색어 리스트 갱신
            initRecentSearch();
        });

        binding.searchHistoryDeleteBtn6.setOnClickListener(v -> {
            // 6번이 마지막 번호라면 6번 검색어 삭제
            if (AppKeyValueStore.getValue(getContext(), "searchHistory7") == null) {
                AppKeyValueStore.remove(getContext(), "searchHistory6");
            }
            // 1번 이후로 검색어가 남아있다면 앞으로 한칸씩 땡기고 마지막 번호는 삭제
            else {
                for (int i=6; i<=9; ++i) {
                    if (AppKeyValueStore.getValue(getContext(), "searchHistory"+(i+1)) != null) {
                        AppKeyValueStore.put(getContext(), "searchHistory"+i,
                                AppKeyValueStore.getValue(getContext(), "searchHistory"+(i+1)));
                        AppKeyValueStore.remove(getContext(), "searchHistory"+(i+1));
                    }
                }
            }
            // 최근 검색어 리스트 갱신
            initRecentSearch();
        });

        binding.searchHistoryDeleteBtn7.setOnClickListener(v -> {
            // 7번이 마지막 번호라면 7번 검색어 삭제
            if (AppKeyValueStore.getValue(getContext(), "searchHistory8") == null) {
                AppKeyValueStore.remove(getContext(), "searchHistory7");
            }
            // 7번 이후로 검색어가 남아있다면 앞으로 한칸씩 땡기고 마지막 번호는 삭제
            else {
                for (int i=7; i<=9; ++i) {
                    if (AppKeyValueStore.getValue(getContext(), "searchHistory"+(i+1)) != null) {
                        AppKeyValueStore.put(getContext(), "searchHistory"+i,
                                AppKeyValueStore.getValue(getContext(), "searchHistory"+(i+1)));
                        AppKeyValueStore.remove(getContext(), "searchHistory"+(i+1));
                    }
                }
            }
            // 최근 검색어 리스트 갱신
            initRecentSearch();
        });

        binding.searchHistoryDeleteBtn8.setOnClickListener(v -> {
            // 8번이 마지막 번호라면 8번 검색어 삭제
            if (AppKeyValueStore.getValue(getContext(), "searchHistory9") == null) {
                AppKeyValueStore.remove(getContext(), "searchHistory8");
            }
            // 1번 이후로 검색어가 남아있다면 앞으로 한칸씩 땡기고 마지막 번호는 삭제
            else {
                for (int i=8; i<=9; ++i) {
                    if (AppKeyValueStore.getValue(getContext(), "searchHistory"+(i+1)) != null) {
                        AppKeyValueStore.put(getContext(), "searchHistory"+i,
                                AppKeyValueStore.getValue(getContext(), "searchHistory"+(i+1)));
                        AppKeyValueStore.remove(getContext(), "searchHistory"+(i+1));
                    }
                }
            }
            // 최근 검색어 리스트 갱신
            initRecentSearch();
        });

        binding.searchHistoryDeleteBtn9.setOnClickListener(v -> {
            // 9번이 마지막 번호라면 9번 검색어 삭제
            if (AppKeyValueStore.getValue(getContext(), "searchHistory10") == null) {
                AppKeyValueStore.remove(getContext(), "searchHistory9");
            }
            // 9번 이후로 검색어가 남아있다면 앞으로 한칸씩 땡기고 마지막 번호는 삭제
            else {
                for (int i=9; i<=9; ++i) {
                    if (AppKeyValueStore.getValue(getContext(), "searchHistory"+(i+1)) != null) {
                        AppKeyValueStore.put(getContext(), "searchHistory"+i,
                                AppKeyValueStore.getValue(getContext(), "searchHistory"+(i+1)));
                        AppKeyValueStore.remove(getContext(), "searchHistory"+(i+1));
                    }
                }
            }
            // 최근 검색어 리스트 갱신
            initRecentSearch();
        });

        binding.searchHistoryDeleteBtn10.setOnClickListener(v -> {
            // 10번 검색어 삭제
            AppKeyValueStore.remove(getContext(), "searchHistory10");
            // 최근 검색어 리스트 갱신
            initRecentSearch();
        });




    }

}